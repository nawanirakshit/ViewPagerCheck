package rakshit.viewpager.check

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var list: ArrayList<String>
    private lateinit var pagerAdapter: ScreenSlidePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        list = arrayListOf();
        list.add("45")
        val mViewPager: ViewPager2 = findViewById(R.id.view_pager);

        pagerAdapter = ScreenSlidePagerAdapter(this)
        mViewPager.adapter = pagerAdapter

        val mAddAtNext: AppCompatButton = findViewById(R.id.btn_next);
        val mAddAtPrevious: AppCompatButton = findViewById(R.id.btn_previous);

        mAddAtNext.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    val randomNext = (50..90).random()
                    println("randomNext >>>>>> $randomNext")
                    pagerAdapter.addFragment("Next $randomNext")

                }, 200
            )
        }

        mAddAtPrevious.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    val randomPrevious = (0..40).random()
                    println("randomPrevious >>>>>> $randomPrevious")
                    pagerAdapter.addAtPreviousFragment("Previous $randomPrevious")
                }, 200
            )
        }

    }

    inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int {
            return list.size
        }

        override fun createFragment(position: Int): Fragment = ScrollFragment(list[position])

        fun addFragment(string: String) {
            list.add(string)
            notifyItemInserted(list.size)
        }

        fun addAtPreviousFragment(string: String) {
            list.add(0, string)
            notifyItemInserted(0)
        }
    }
}