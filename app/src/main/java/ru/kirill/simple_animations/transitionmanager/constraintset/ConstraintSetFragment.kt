package ru.kirill.simple_animations.transitionmanager.constraintset

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import ru.kirill.simple_animations.R
import ru.kirill.simple_animations.databinding.FragmentConstraintSetBinding

class ConstraintSetFragment : Fragment() {

    private var _binding: FragmentConstraintSetBinding? = null
    private val binding get() = _binding!!

    private var isOpen:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConstraintSetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animate()
    }

    private fun animate(){
        binding.button.setOnClickListener {

            val constraintSet = ConstraintSet()
            constraintSet.clone(binding.container)

            val transition = ChangeBounds()
            transition.interpolator = BounceInterpolator()
            transition.duration = 1000
            TransitionManager.beginDelayedTransition(binding.container, transition)

            if(isOpen){
                constraintSet.connect(
                    R.id.text_one, ConstraintSet.RIGHT,
                    R.id.background, ConstraintSet.RIGHT)
                constraintSet.connect(R.id.text_two, ConstraintSet.TOP, R.id.button, ConstraintSet.BOTTOM)
            }else{
                constraintSet.connect(
                    R.id.text_one, ConstraintSet. RIGHT,
                    R.id.background, ConstraintSet.LEFT)
                constraintSet.connect(
                    R.id.text_two, ConstraintSet.TOP,
                    R.id.background, ConstraintSet.BOTTOM)
            }

            constraintSet.applyTo(binding.container)

            isOpen = !isOpen
        }

    }


    companion object {
        @JvmStatic
        fun newInstance() =
            ConstraintSetFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}