package com.softdough.grow.presentation.ui.Routine

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.softdough.grow.R
import com.softdough.grow.databinding.DialogCreateCategoryBinding
import com.softdough.grow.databinding.FragmentRoutineBinding
import com.softdough.grow.databinding.ViewRoutineBinding
import com.softdough.grow.databinding.ViewRoutineCollectionBinding
import com.softdough.grow.domain.model.CategoryItem
import com.softdough.grow.presentation.base.BaseDialogFragment
import com.softdough.grow.presentation.base.BaseFragment
import com.softdough.grow.presentation.startActivity
import com.softdough.grow.presentation.ui.CreateRoutine.CreateRoutine
import com.softdough.grow.presentation.ui.Workout.WorkoutActivity
import com.softdough.grow.util.ScreenUtil


abstract class RoutineFragment<T_VIEW_MODEL : ViewModel> : BaseFragment<FragmentRoutineBinding>() {

    abstract val vm: RoutineViewModel
    override val resourceId: Int = R.layout.fragment_routine
    protected val createCategoryDialog = CreateCategoryDialog()

    val BUNDLE_CATEGORY_ID = "categoryId"
    val BUNDLE_ROUTINE_ID = "routineId"

    val REQUEST_CODE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createCategoryDialog.onDialogCallback = createCategory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    //Render
    protected fun renderCategories() {
        binding.routineCollectionViewPager.apply {
            adapter = RoutineCollectionPagerAdapter(
                categoryList = vm.liveCategories.value!!,
                context = context
            )
            vm.currentPage.postValue(currentItem)

            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {}

                override fun onPageScrollStateChanged(state: Int) {
                    //indicator update
                    if (state == ViewPager.SCROLL_STATE_SETTLING) {
                        vm.currentPage.postValue(currentItem)
                    }
                }
            })
        }

        binding.routineCollectionIndicator.apply {
            pageCount = vm.liveCategories.value!!.size
            updateDotState()
        }
    }

    protected fun renderRoutines(page: Int) {
        binding.routineGridLayout.apply {
            var childWidth = (ScreenUtil.deviceWidth() - (ScreenUtil.DP_16 * 3)) / 2

            removeAllViews()

            //루틴이 존재하지 않을 떄
            when (vm.liveCategoryIdRoutineBindings.value?.get(page)?.routines?.size == 0) {
                true -> {
                    binding.routineDescription.text = "아직 루틴이 존재하지 않습니다. 새로 루틴을 생성하세요."
                    binding.nonExistDetailRoutineImage.visibility = View.VISIBLE
                }

                false -> {
                    //루틴이 존재할 떄
                    binding.routineDescription.text = "원하는 방식으로 루틴을 구성하세요."
                    binding.nonExistDetailRoutineImage.visibility = View.INVISIBLE

                    vm.liveCategoryIdRoutineBindings.value?.get(page)
                        ?.routines?.forEachIndexed { index, item ->
                        var viewRoutineBinding: ViewRoutineBinding =
                            DataBindingUtil.inflate(
                                layoutInflater,
                                R.layout.view_routine,
                                this,
                                false
                            )

                        var params =
                            ConstraintLayout.LayoutParams(viewRoutineBinding.root.layoutParams)

                        viewRoutineBinding.root.layoutParams = params.apply {
                            width = childWidth
                            leftMargin = ScreenUtil.DP_16

                            if (index >= 2) {
                                topMargin = ScreenUtil.DP_14 * 1
                            }
                        }

                        viewRoutineBinding.routineWrapper.setOnClickListener{
                            startWorkout(item.id)
                        }

                        Glide.with(context)
                            .asBitmap()
                            .load(item.url)
                            .centerCrop()
                            .into(viewRoutineBinding.routineImage)

                        viewRoutineBinding.routineTitle.text = item.name

                        this.addView(viewRoutineBinding.root)
                    }
                }
            }
        }
    }

    protected fun renderAfterLoaded(exist: Boolean) {

        //INVISIBLE
        binding.loadingPanel.visibility = View.INVISIBLE

        when (exist) {
            true -> {
                binding.nonExistContent.visibility = View.GONE
                binding.existContent.visibility = View.VISIBLE
                binding.floatingButton.apply {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        var bundle = Bundle()
                        bundle.putLong(
                            BUNDLE_CATEGORY_ID,
                            vm.liveCategories.value?.get(vm.currentPage.value!!)?.id!!
                        )

                        startActivity<CreateRoutine>(bundle)
                    }
                }
            }
            false -> {
                binding.nonExistContent.visibility = View.VISIBLE
                binding.floatingButton.visibility = View.GONE
                binding.nonExistButton.setOnClickListener {
                    createCategoryDialog.show(activity!!.supportFragmentManager, null)
                }
            }
        }

        //TEXT
        binding.routineCollectionTitle.apply {
            text = "사용자가 정한 이름"
        }
        binding.routineTitle.apply {
            text = "세부 루틴"
        }
        binding.routineDescription.apply {
            text = "원하는 방식으로 루틴을 구성하세요"
        }
    }

    //pager
    protected fun changeCurrentPage() {
        binding.routineCollectionIndicator.apply {
            currentPage = vm.currentPage.value!!

            //TODO 카테로리 이름 맵핑
            //binding.routineCollectionTitle.text = vm.liveCategories.value?.get(currentPage)?.name
            updateDotState()
        }

        renderRoutines(vm.currentPage.value!!)
    }

    private fun createCategory(): () -> Unit = {
        vm.setCategory("CUSTOM", createCategoryDialog.binding.dialogEdit.text.toString())
    }

    private fun startWorkout(routineId : Long) = run {
        var bundle = Bundle()
        bundle.putLong(
            BUNDLE_ROUTINE_ID,
            routineId
        )

        startActivity<WorkoutActivity>(bundle)
    }
}

class RoutineCollectionPagerAdapter(
    private val categoryList: List<CategoryItem>,
    private val context: Context
) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return categoryList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var binding: ViewRoutineCollectionBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.view_routine_collection,
                container,
                false
            )

        Glide.with(context)
            .asBitmap()
            .load(categoryList[position].url)
            .centerCrop()
            .into(binding.collectionImage)

        container.addView(binding.root)

        return binding.root
    }
}

class CreateCategoryDialog : BaseDialogFragment<DialogCreateCategoryBinding>() {
    override val resourceId: Int = R.layout.dialog_create_category
    var onDialogCallback: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dialogButton.setOnClickListener {
            onDialogCallback?.invoke()
            dismiss()
        }
    }
}