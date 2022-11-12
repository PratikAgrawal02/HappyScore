package com.pratik.happyscore.auth


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.pratik.happyscore.R


class Questionare : AppCompatActivity() {
    var income = arrayOf(
        "Below Rs. 2,00,000",
        "between Rs. 2,00,000 to 5,00,000",
        "between Rs. 5,00,000 to 20,00,000",
        "above Rs. 20,000,000"
    )
    var education =
        arrayOf("no education", "primary education", "secondary education", "graduation")
    var employment = arrayOf("employed", "not employed")
    var foodAvailability = arrayOf("not available", "struggling", "easily available")
    var housingAvailability =
        arrayOf("no house and can't afford", "no house but can afford", "available")
    var socialDiscrimination = arrayOf("Yes", "No")
    var medicalTreatment = arrayOf(
        "not on any medication",
        "under basic medication",
        "under severe medication",
        "bed ridden"
    )

    lateinit var tincome: Spinner
    lateinit var teducation:Spinner
    lateinit var temployment:Spinner
    lateinit var tfoodAvailability:Spinner
    lateinit var thousingAvailability:Spinner
    lateinit var tsocialDiscrimination:Spinner
    lateinit var tmedicalTreatment:Spinner
    lateinit var aincome: ArrayAdapter<String>
    lateinit var aeducation:ArrayAdapter<String>
    lateinit var aemployment:ArrayAdapter<String?>
    lateinit var afoodAvailability:ArrayAdapter<String>
    lateinit var ahousingAvailability:ArrayAdapter<String>
    lateinit var asocialDiscrimination:ArrayAdapter<String>
    lateinit var amedicalTreatment:ArrayAdapter<String?>

    lateinit var incomeAns: String
    lateinit var educationAns:kotlin.String
    lateinit var employmentAns:kotlin.String
    lateinit var foodAvailabilityAns:kotlin.String
    lateinit var housingAvailabilityAns:kotlin.String
    lateinit var socialDiscriminationAns:kotlin.String
    lateinit var medicalTreatmentAns:kotlin.String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionare)
        aincome = ArrayAdapter(this, R.layout.list_items2, income)
        aeducation = ArrayAdapter(this, R.layout.list_items2, education)
        aemployment = ArrayAdapter(this, R.layout.list_items2, employment)
        afoodAvailability = ArrayAdapter(this, R.layout.list_items2, foodAvailability)
        ahousingAvailability = ArrayAdapter(this, R.layout.list_items2, housingAvailability)
        asocialDiscrimination = ArrayAdapter(this, R.layout.list_items2, socialDiscrimination)
        amedicalTreatment = ArrayAdapter(this, R.layout.list_items2, medicalTreatment)
        tincome = findViewById(com.pratik.happyscore.R.id.income)
        teducation = findViewById(R.id.education)
        temployment = findViewById(R.id.employment)
        tfoodAvailability = findViewById(R.id.food)
        thousingAvailability = findViewById(R.id.housing)
        tsocialDiscrimination = findViewById(R.id.social)
        tmedicalTreatment = findViewById(R.id.medical)
        tincome.adapter = aincome
        temployment.adapter = aemployment
        teducation.adapter = aeducation
        tfoodAvailability.adapter = afoodAvailability
        thousingAvailability.adapter = ahousingAvailability
        tsocialDiscrimination.adapter = asocialDiscrimination
        tmedicalTreatment.adapter = amedicalTreatment
        tincome.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                incomeAns = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        temployment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                employmentAns = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        teducation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                educationAns = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        tfoodAvailability.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                foodAvailabilityAns = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        thousingAvailability.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                housingAvailabilityAns = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        tsocialDiscrimination.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                socialDiscriminationAns = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        tmedicalTreatment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                medicalTreatmentAns = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}