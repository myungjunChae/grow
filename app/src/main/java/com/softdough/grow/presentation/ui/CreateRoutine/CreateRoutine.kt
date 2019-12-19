package com.softdough.grow.presentation.ui.CreateRoutine

import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import androidx.appcompat.view.menu.MenuView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.softdough.grow.R
import com.softdough.grow.databinding.ActivityCreateRoutineBinding
import com.softdough.grow.databinding.ViewExerciseBinding
import com.softdough.grow.databinding.ViewPartialBinding
import com.softdough.grow.presentation.base.BaseActivity
import com.softdough.grow.presentation.bindColor
import com.softdough.grow.presentation.startActivityWithFinish
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateRoutine : BaseActivity<ActivityCreateRoutineBinding>() {
    private val vm: CreateRoutineViewModel by viewModel()

    override val resourceId: Int = R.layout.activity_create_routine
    override val statusBarColor: Int by bindColor(R.color.colorWhite)

    val BUNDLE_CATEGORY_ID = "categoryId"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var categoryId = intent.getLongExtra(BUNDLE_CATEGORY_ID, 0)

        binding.floatingButton.setOnClickListener {
            createRoutine(categoryId, "test")
        }

        vm.livePartialExerciseBinding.observe(this, Observer {
            binding.recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(this@CreateRoutine, LinearLayoutManager.VERTICAL, false)

                adapter = ExerciseRecyclerAdapter(it).apply {
                    onPropagationCallback = {
                        onExerciseClickCallback(it)
                    }
                    notifyDataSetChanged()
                }
            }
        })

        vm.liveRoutine.observe(this, Observer {
            vm.exerciseList.forEach { exerciseId ->
                vm.linkRoutineExercise(it.id, exerciseId)
            }
        })

        vm.getExerciseAll(false)
    }

    private fun onExerciseClickCallback(exerciseId: Long) {
        vm.addExercise(exerciseId)
    }

    private fun createRoutine(categoryId: Long, routineName: String) {
        categoryId?.run {
            vm.setRoutine(this, "test")
        }

        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}

class ExerciseRecyclerAdapter(private val partialExerciseBindingList: List<PartialExerciseBinding>) :
    RecyclerView.Adapter<ExerciseRecyclerAdapter.ItemViewHolder>() {
    private lateinit var context: Context
    private lateinit var viewHolder: ItemViewHolder

    private val VIEW_PARTIAL = 0
    private val VIEW_EXERCISE = 1

    var onPropagationCallback: ((Long) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context = parent.context

        var inflater = LayoutInflater.from(context)

        when (viewType) {
            VIEW_EXERCISE -> {
                var binding = DataBindingUtil.inflate<ViewExerciseBinding>(
                    inflater,
                    R.layout.view_exercise,
                    parent,
                    false
                )
                viewHolder = ExerciseViewHolder(binding, context).apply {
                    onClickCallback = {
                        onPropagationCallback?.invoke(it)
                    }
                }
            }
            VIEW_PARTIAL -> {
                var binding = DataBindingUtil.inflate<ViewPartialBinding>(
                    inflater,
                    R.layout.view_partial,
                    parent,
                    false
                )
                viewHolder = PartialViewHolder(binding)
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindData(partialExerciseBindingList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when (partialExerciseBindingList[position].type) {
            PartialExercise.Exercise -> {
                VIEW_EXERCISE
            }
            else -> {
                VIEW_PARTIAL
            }
        }
    }

    override fun getItemCount(): Int {
        return partialExerciseBindingList.size
    }

    abstract class ItemViewHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bindData(data: PartialExerciseBinding)
    }

    class PartialViewHolder(private val binding: ViewPartialBinding) : ItemViewHolder(binding) {
        override fun bindData(data: PartialExerciseBinding) {
            binding.textView.text = data.partial
        }

    }

    class ExerciseViewHolder(
        private val binding: ViewExerciseBinding,
        private val context: Context
    ) : ItemViewHolder(binding) {
        var exerciseId: Long = 0
        var checkState = false
        var onClickCallback: ((Long) -> Unit)? = null

        init {
            this.itemView.setOnClickListener {
                onClickCallback?.invoke(exerciseId)

                when (checkState) {
                    false -> {
                        binding.checkBox.background =
                            ContextCompat.getDrawable(context, R.drawable.ic_check_box)
                    }
                    true -> {
                        binding.checkBox.background =
                            ContextCompat.getDrawable(context, R.drawable.ic_uncheck_box)
                    }
                }
                checkState = !checkState
            }
        }

        override fun bindData(data: PartialExerciseBinding) {
            exerciseId = data.exercise?.id!!

            binding.textView.text = data.exercise
                ?.name
                ?: "null"
        }
    }
}