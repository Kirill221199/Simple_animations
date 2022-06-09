package ru.kirill.simple_animations

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.transition.ArcMotion
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import ru.kirill.simple_animations.databinding.FragmentTrajectoryMoveBinding

class TrajectoryMoveFragment : Fragment() {

    private var _binding: FragmentTrajectoryMoveBinding? = null
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
        _binding = FragmentTrajectoryMoveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        move()
    }

    private fun move(){
        binding.button.setOnClickListener {

            val transitionChangeBounds = ChangeBounds()
            transitionChangeBounds.duration = 3000
            val arc = ArcMotion()
            arc.minimumVerticalAngle = 70f // устанавливаем угол радиальной траектории
            transitionChangeBounds.setPathMotion(arc)

            TransitionManager.beginDelayedTransition(binding.root, transitionChangeBounds)

            val params = (binding.button.layoutParams as FrameLayout.LayoutParams)

            // устанавливаем конечные точки траектории
            if(isOpen){
                params.gravity = Gravity.BOTTOM or Gravity.END
            }else{
                params.gravity = Gravity.TOP or Gravity.START
            }
            binding.button.layoutParams = params

            isOpen = !isOpen
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TrajectoryMoveFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}