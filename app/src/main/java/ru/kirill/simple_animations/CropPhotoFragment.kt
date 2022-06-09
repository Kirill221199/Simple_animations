package ru.kirill.simple_animations

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import ru.kirill.simple_animations.databinding.FragmentCropPhotoBinding

class CropPhotoFragment : Fragment() {

    private var _binding: FragmentCropPhotoBinding? = null
    private val binding get() = _binding!!

    private var isOpen: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCropPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cropPhoto()
    }

    @SuppressLint("ResourceAsColor")
    private fun cropPhoto(){
        binding.image.setOnClickListener {

            val transitionImage = ChangeImageTransform()// отвечает за анимацию картинки
            transitionImage.duration = 3000
            val transitionChangeBounds = ChangeBounds()
            transitionChangeBounds.duration = 3000

            val transitionSet = TransitionSet()
            transitionSet.addTransition(transitionImage)
            transitionSet.addTransition(transitionChangeBounds)
            TransitionManager.beginDelayedTransition(binding.root, transitionSet)

            // настраиваем вид картинки после клика
            val params = (binding.image.layoutParams as ConstraintLayout.LayoutParams)
            if (isOpen){
                params.height = FrameLayout.LayoutParams.MATCH_PARENT
            }else{
                params.height = FrameLayout.LayoutParams.WRAP_CONTENT
            }
            binding.image.layoutParams = params

            if (isOpen){
                binding.image.scaleType = ImageView.ScaleType.CENTER_CROP
            }else{
                binding.image.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }

            isOpen = !isOpen
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CropPhotoFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}