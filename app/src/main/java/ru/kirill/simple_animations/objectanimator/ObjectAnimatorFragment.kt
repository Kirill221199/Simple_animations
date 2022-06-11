package ru.kirill.simple_animations.objectanimator

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.kirill.simple_animations.databinding.FragmentObjectAnimatorBinding

class ObjectAnimatorFragment : Fragment() {

    private var _binding: FragmentObjectAnimatorBinding? = null
    private val binding get() = _binding!!

    private var isOpen:Boolean = true
    private val duration: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentObjectAnimatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animator()
    }

    private fun animator(){
        binding.fab.setOnClickListener {
            if (isOpen) {
                ObjectAnimator.ofFloat(binding.plusImageview, View.ROTATION, 0f, 225f)
                    .setDuration(duration).start() // вращение картинки в градусах (в кнопке fub)
                ObjectAnimator.ofFloat(binding.optionFirstContainer, View.TRANSLATION_Y,  -260f)
                    .setDuration(duration).start() // "выползание" элемента на заданное значение
                ObjectAnimator.ofFloat(binding.optionSecondContainer, View.TRANSLATION_Y,  -130f)
                    .setDuration(duration).start() // "выползание" элемента на заданное значение

                // анимация самого "выползания"
                binding.optionFirstContainer.animate()
                    .alpha(1f)
                    .setDuration(duration / 2)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            binding.optionFirstContainer.isClickable = true
                        }
                    })

                // анимация самого "выползания"
                binding.optionSecondContainer.animate()
                    .alpha(1f)
                    .setDuration(duration / 2)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            binding.optionSecondContainer.isClickable = true
                        }
                    })

                // изменение альфы (затемнение при нажатии на кнопку)
                binding.transparentBackground.animate()
                    .alpha(0.5f)
                    .setDuration(duration)
            } else {
                ObjectAnimator.ofFloat(binding.plusImageview, View.ROTATION, 225f, 0f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionFirstContainer, View.TRANSLATION_Y, 0f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionSecondContainer, View.TRANSLATION_Y, 0f)
                    .setDuration(duration).start()

                // анимация "заползпния"
                binding.optionFirstContainer.animate()
                    .alpha(0f)
                    .setDuration(duration / 2)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            binding.optionFirstContainer.isClickable = false
                        }
                    })

                // анимация "заползпния"
                binding.optionSecondContainer.animate()
                    .alpha(0f)
                    .setDuration((duration / 1.5).toLong())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            binding.optionSecondContainer.isClickable = false
                        }
                    })

                // изменение альфы (зануляем альфу при втором нажатии на кнопку)
                binding.transparentBackground.animate()
                    .alpha(0f)
                    .setDuration(duration)

            }
            isOpen = !isOpen
        }
    }



    companion object {
        @JvmStatic
        fun newInstance() =
            ObjectAnimatorFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}