package vn.edu.vlsc.lab1helloworld

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import vn.edu.vlsc.lab1helloworld.databinding.ActivityDetailBinding
import vn.edu.vlsc.lab1helloworld.model.Student

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_STUDENT = "vn.edu.vlsc.lab1helloworld.EXTRA_STUDENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyWindowInsets()
        extractAndBindData()
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.detailMain) { view, insets ->
            val types = WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            val bars = insets.getInsets(types)
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }
    }

    private fun extractAndBindData() {
        val student: Student? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_STUDENT, Student::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_STUDENT)
        }

        student?.let { currentStudent ->
            binding.tvDetailName.text = currentStudent.name
            binding.tvDetailMssv.text = getString(R.string.student_mssv, currentStudent.mssv)
            binding.imgDetailAvatar.setImageResource(currentStudent.avatarRes)

            binding.btnShare.setOnClickListener {
                executeShareIntent(currentStudent)
            }
        } ?: run {
            binding.tvDetailName.text = "Lỗi: Khuyết dữ liệu định tuyến."
        }
    }

    private fun executeShareIntent(student: Student) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Hồ sơ SV: ${student.name} - ${student.mssv}")
            type = "text/plain"
        }
        val chooser = Intent.createChooser(sendIntent, getString(R.string.share_chooser_title))
        startActivity(chooser)
    }
}
