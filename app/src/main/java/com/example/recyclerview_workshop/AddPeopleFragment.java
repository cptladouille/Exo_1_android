package com.example.recyclerview_workshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import androidx.fragment.app.DialogFragment;

public class AddPeopleFragment extends DialogFragment {

private EditText name;
private EditText age;
private People.Sex sex;
private boolean loveAndroid;
    public interface AddPeopleListener {

        void onCreatePeopleListener(People p);

    }
    static AddPeopleFragment newInstance() {
        AddPeopleFragment f = new AddPeopleFragment();
        Bundle args = new Bundle();
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        setStyle(style, theme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.add_people_fragment, container,false);
        Button createBtn = fragmentView.findViewById(R.id.PeopleCreateBtn);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindDatas(fragmentView);
                AddPeopleListener listener = (AddPeopleListener) getActivity();
                listener.onCreatePeopleListener(TryCreatePeople());
                dismiss();

            }
        });

        Button cancelBtn = fragmentView.findViewById(R.id.PeopleCancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return fragmentView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        int width = getResources().getDimensionPixelSize(R.dimen.AddPeopleFragmentWidth);
        int height = getResources().getDimensionPixelSize(R.dimen.AddPeopleFragmentHeight);
        getDialog().getWindow().setLayout(width,height);
    }


    private void bindDatas(View v)
    {
        RadioGroup radioGroup = v.findViewById(R.id.PeopleSexGroupSelector);
        name = v.findViewById(R.id.PeopleNameInput);
        age =  v.findViewById(R.id.PeopleAgeInput);
        Switch loveAndroidSwitch = v.findViewById(R.id.PeopleLoveAndroidSwitch);
        if(radioGroup.getCheckedRadioButtonId() == R.id.PeopleSexRadioBtnMale) {
            sex = People.Sex.MALE;
        }
        else if (radioGroup.getCheckedRadioButtonId() == R.id.PeopleSexRadioBtnFemale){
            sex = People.Sex.FEMALE;
        }
        else{
            sex = null;
        }
        loveAndroid = loveAndroidSwitch.isChecked();
    }

    private People TryCreatePeople()
    {
        return new People(name.getText().toString(), Integer.parseInt(age.getText().toString()), sex, loveAndroid);
    }

}
