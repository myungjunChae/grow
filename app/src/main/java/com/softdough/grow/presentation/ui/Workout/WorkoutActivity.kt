package com.softdough.grow.presentation.ui.Workout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.softdough.grow.R
import com.softdough.grow.databinding.ActivityWorkoutBinding
import com.softdough.grow.databinding.ViewWorkoutHolderBinding
import com.softdough.grow.domain.model.Exercise
import com.softdough.grow.domain.model.ExerciseItem
import com.softdough.grow.presentation.base.BaseActivity
import com.softdough.grow.presentation.bindColor
import org.koin.androidx.viewmodel.ext.android.viewModel

class WorkoutActivity : BaseActivity<ActivityWorkoutBinding>() {
    private val vm: WorkoutViewModel by viewModel()
    override val resourceId: Int = R.layout.activity_workout
    override val statusBarColor: Int by bindColor(R.color.colorWhite)

    val BUNDLE_ROUTINE_ID = "routineId"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var routineId = intent.getLongExtra(BUNDLE_ROUTINE_ID, 0)

        binding

        vm.liveExercises.observe(this, Observer {
            binding.recyclerView.apply {
                layoutManager =
                    LinearLayoutManager(this@WorkoutActivity, LinearLayoutManager.VERTICAL, false)

                adapter = WorkoutRecyclerAdapter(it).apply {
                    notifyDataSetChanged()
                }
            }
        })

        vm.getExercise(routineId, false)
    }
}

class WorkoutRecyclerAdapter(private val exerciseList: List<ExerciseItem>) :
    RecyclerView.Adapter<WorkoutRecyclerAdapter.ViewHolder>() {
    private lateinit var context: Context
    private lateinit var viewHolder: WorkoutRecyclerAdapter.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        var inflater = LayoutInflater.from(context)
        var binding = DataBindingUtil.inflate<ViewWorkoutHolderBinding>(
            inflater,
            R.layout.view_workout_holder,
            parent,
            false
        )

        viewHolder = ViewHolder(binding)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(exerciseList[position])
    }

    class ViewHolder(private val binding: ViewWorkoutHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: ExerciseItem) {
            binding.textView.text = data.name
        }
    }
}