package ru.kirill.simple_animations

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Fade
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import ru.kirill.simple_animations.databinding.FragmentCircularAnimationMenuButtonStartBinding

class CircularAnimationMenuButtonFragment : Fragment() {

    private var _binding: FragmentCircularAnimationMenuButtonStartBinding? = null
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
        _binding = FragmentCircularAnimationMenuButtonStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        circularAnimation()
    }

    private fun circularAnimation(){
        binding.mainFab.setOnClickListener {

            // создаём сеты констрейтов для начального и конечного положения кнопок на экране (клонировав макеты)
            val constraintSetStart = ConstraintSet()
            constraintSetStart.clone(requireContext(), R.layout.fragment_circular_animation_menu_button_start)
            val constraintSetEnd = ConstraintSet()
            constraintSetEnd.clone(requireContext(), R.layout.fragment_circular_animation_menu_button_end)

            // настраиваем сет транзиций
            val transitionChangeBounds = ChangeBounds()
            transitionChangeBounds.duration = 2000
            val transitionFade = Fade()
            transitionFade.duration = 2000

            val transitionSet = TransitionSet()
            transitionSet.addTransition(transitionChangeBounds)
            transitionSet.addTransition(transitionFade)
            transitionSet.interpolator = AnticipateOvershootInterpolator(5f)

            TransitionManager.beginDelayedTransition(binding.root, transitionSet)

            if(isOpen){
                constraintSetEnd.applyTo(binding.root)
            }else{
                constraintSetStart.applyTo(binding.root)
            }

            isOpen = !isOpen
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CircularAnimationMenuButtonFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}