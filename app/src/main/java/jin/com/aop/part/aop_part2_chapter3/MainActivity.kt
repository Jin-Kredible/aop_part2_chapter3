package jin.com.aop.part.aop_part2_chapter3

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private val numberPicker1 : NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker1)
            .apply{
                minValue=0
                maxValue=9
            }

    }

    private val numberPicker2 : NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker2)
            .apply{
                minValue=0
                maxValue=9
            }
    }

    private val numberPicker3 : NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker3)
            .apply{
                minValue=0
                maxValue=9
            }
    }

    private val openButton : AppCompatButton by lazy {
        findViewById(R.id.openButton)
    }

    private val changePasswordButton : AppCompatButton by lazy {
        findViewById(R.id.changePassword)
    }

    private var changePasswordMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker1
        numberPicker2
        numberPicker3

        openButton.setOnClickListener {

            if(changePasswordMode) {
                Toast.makeText(this,"비밀번호 변경 중입니다",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val passwordPref = getSharedPreferences("password", Context.MODE_PRIVATE)

            val passwordFromUsr : String = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if(passwordPref.getString("password","000").equals(passwordFromUsr)) {
//                startActivity()
            }else {
                showErrorAlert()
            }
        }

        changePasswordButton.setOnClickListener {

            val passwordPref = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUsr : String = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"


            if(changePasswordMode) {
                //번호를 저장하는 기능


                passwordPref.edit(true) {
                    putString("password",passwordFromUsr)
                }

                changePasswordMode = false
                changePasswordButton.setBackgroundColor(Color.BLACK)


            } else {
                //Chagne password Mode 활성화 : 비밀번호가 맞는지 체크

                if(passwordPref.getString("password","000").equals(passwordFromUsr)) {
                    changePasswordMode = true
                    Toast.makeText(this,"패스워드를 변경해주세요",Toast.LENGTH_SHORT).show()
                    changePasswordButton.setBackgroundColor(Color.RED)

                }else {
                    showErrorAlert()
                }
            }
        }

    }

    private fun showErrorAlert() {

        AlertDialog.Builder(this)
            .setTitle("실패")
            .setMessage("비밀번호가 잘못되었습니다")
            .setPositiveButton("확인") { _, _ ->
            }.create().show()

    }
}