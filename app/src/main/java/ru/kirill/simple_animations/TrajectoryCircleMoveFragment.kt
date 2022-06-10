package ru.kirill.simple_animations

import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.transition.ArcMotion
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import ru.kirill.simple_animations.databinding.FragmentTrajectoryCircleMoveBinding
import ru.kirill.simple_animations.databinding.FragmentTrajectoryMoveBinding


class TrajectoryCircleMoveFragment : Fragment() {

    private var _binding: FragmentTrajectoryCircleMoveBinding? = null
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
        _binding = FragmentTrajectoryCircleMoveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        move()
    }

    private fun move(){
        binding.button.setOnClickListener {

            // с помощью первых четырех параметров вы определяете четыре края прямоугольника по пикселям
            // пятый параметр - это начальная точка пути от круга на 360 градусов
            // шестой параметр - конечная точка пути в этом кругу
            val path = Path()
            path.arcTo(binding.guidelineLeft.x, (binding.guidelineTop.y - binding.button.height), (binding.guidelineRight.x- binding.button.width), binding.guidelineBottom.y, 180f, 359f, true)

            // первый параметр конструктора - цель анимации
            val animator = ObjectAnimator.ofFloat(
                binding.button,
                View.X,
                View.Y,
                path
            )

            animator.duration = 2000
            animator.interpolator = BounceInterpolator() // интерполятор отскока
            animator.start()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TrajectoryCircleMoveFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}