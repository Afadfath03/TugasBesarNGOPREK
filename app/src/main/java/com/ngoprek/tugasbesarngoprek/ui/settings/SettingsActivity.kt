package com.ngoprek.tugasbesarngoprek.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ngoprek.tugasbesarngoprek.databinding.ActivitySettingsBinding
import com.ngoprek.tugasbesarngoprek.data.model.Reminder
import com.ngoprek.tugasbesarngoprek.preference.ReminderPreference
import com.ngoprek.tugasbesarngoprek.receiver.AlarmReceiver

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var reminder: Reminder
    private lateinit var alarmReceiver: AlarmReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reminderPreference = ReminderPreference(this)
        if (reminderPreference.getReminder().isReminded){
            binding.switch1.isChecked = true
        } else {
            binding.switch1.isChecked = false
        }

        alarmReceiver = AlarmReceiver()

        binding.switch1.setOnCheckedChangeListener{buttonView, isChecked ->
            if(isChecked){
                saveRemider(true)
                alarmReceiver.setRepeatingAlarm(this, "RepeatingAlarm", "07:45", "Github reminder")
            } else{
                saveRemider(false)
                alarmReceiver.cancelAlarm(this)
            }
        }
    }

    private fun saveRemider(state: Boolean) {
        val reminderPreference = ReminderPreference(this)
        reminder = Reminder()
        reminder.isReminded = state
        reminderPreference.setReminder(reminder)
    }
}