package ru.kirill.simple_animations.transitionmanager

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import ru.kirill.simple_animations.R
import ru.kirill.simple_animations.databinding.FragmentExplodeTransitionBinding

class ExplodeTransitionFragment : Fragment() {

    private var _binding: FragmentExplodeTransitionBinding? = null
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
        _binding = FragmentExplodeTransitionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = Adapter()
    }

    //region Adapter
    inner class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return ViewHolder(
                LayoutInflater.from(requireContext()).inflate(
                    R.layout.explode_recycler_item,
                    parent,
                    false
                ) as View
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder.itemView.setOnClickListener { button ->

                // определяем элемент как прямоугольник
                val epicenter = Rect()
                button.getGlobalVisibleRect(epicenter)

                val transitionExplode = Explode() //  отвечает за "разлёт" элементов
                transitionExplode.epicenterCallback = object : Transition.EpicenterCallback() {
                    override fun onGetEpicenter(transition: Transition): Rect {
                        return epicenter
                    }
                }
                transitionExplode.duration = 3000
                transitionExplode.excludeTarget(button, true) // исключаем кнопку из транзиции

                val transitionFadeButton = Fade()
                transitionFadeButton.duration = 3000

                val transitionFade = Fade()
                transitionFade.duration =
                    9999999999999 // устанавливаем чтобы кнопки не пропадала

                val transitionSet = TransitionSet()
                transitionSet.addTransition(transitionExplode)
                transitionSet.addTransition(transitionFadeButton)
                //transitionSet.addTransition(transitionFade)

                TransitionManager.beginDelayedTransition(binding.recyclerView, transitionSet)
                binding.recyclerView.adapter = null

            }

        }

        override fun getItemCount(): Int {
            return 28
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    //endregion


    companion object {
        @JvmStatic
        fun newInstance() =
            ExplodeTransitionFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}