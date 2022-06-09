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
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import ru.kirill.simple_animations.databinding.FragmentSimpleTransitionBinding

class SimpleTransitionFragment : Fragment() {

    private var _binding: FragmentSimpleTransitionBinding? = null
    private val binding get() = _binding!!

    private var isOpen: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSimpleTransitionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        simpleTransition()
    }

    private fun simpleTransition(){
        binding.button.setOnClickListener {
            val transitionFade = Fade() // отвечает за видимость объекта (проявление и исчезание) при анимации
            transitionFade.duration = 1500 // время проявления транзиции

            val transitionChangeBounds = ChangeBounds() // отвечает за размероы и положения при анимации
            transitionChangeBounds.interpolator = AnticipateOvershootInterpolator(5f) // интерполяция
            transitionChangeBounds.duration = 1500

            val transitionSet = TransitionSet() // служит для объединения нескольких транзиций
            transitionSet.addTransition(transitionFade)
            transitionSet.addTransition(transitionChangeBounds)

            TransitionManager.beginDelayedTransition(binding.root, transitionSet) // объявляем анимацию

            isOpen = !isOpen
            binding.text.visibility = if (isOpen) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SimpleTransitionFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}