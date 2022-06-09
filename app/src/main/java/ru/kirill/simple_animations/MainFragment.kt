package ru.kirill.simple_animations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import ru.kirill.simple_animations.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseAnimationFragment()
    }

    private fun chooseAnimationFragment(){
        binding.fragmentSimpleTransition.setOnClickListener {
            parentFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.container,SimpleTransitionFragment.newInstance()).addToBackStack("").commit()
        }

        binding.fragmentExplodeTrensition.setOnClickListener {
            parentFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.container,ExplodeTransitionFragment.newInstance()).addToBackStack("").commit()
        }

        binding.fragmentCropPhoto.setOnClickListener {
            parentFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.container,CropPhotoFragment.newInstance()).addToBackStack("").commit()
        }

        binding.fragmentTrajectoryMove.setOnClickListener {
            parentFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.container,TrajectoryMoveFragment.newInstance()).addToBackStack("").commit()
        }

        binding.fragmentObjectAnimator.setOnClickListener {
            parentFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.container,ObjectAnimatorFragment.newInstance()).addToBackStack("").commit()
        }

        binding.fragmentStateListAnimator.setOnClickListener {
            parentFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.container,StateListAnimatorFragment.newInstance()).addToBackStack("").commit()
        }

        binding.fragmentConstraintSet.setOnClickListener {
            parentFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.container,ConstraintSetFragment.newInstance()).addToBackStack("").commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MainFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}