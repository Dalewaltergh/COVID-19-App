package com.walter.covid19app.ui.faq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.walter.covid19app.Adapter.QuestionAdapter;
import com.walter.covid19app.Questions;
import com.walter.covid19app.R;

import java.util.ArrayList;
import java.util.List;

public class FAQFragment extends Fragment {

    RecyclerView recyclerView;
    List<Questions> questionsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_faq, container, false);

        recyclerView = root.findViewById(R.id.faqRecyclerView);

        initData();
        setRecyclerView();
        return root;
    }

    private void setRecyclerView() {
        QuestionAdapter adapter = new QuestionAdapter(questionsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        questionsList = new ArrayList<>();

        questionsList.add(new Questions(
                "What is a novel coronavirus?",
                "A novel coronavirus is a new coronavirus that has not been previously identified." +
                        " The virus causing coronavirus disease 2019 (COVID-19), is not the same as" +
                        "the coronaviruses that commonly circulate among humans and cause mild illness, like the common cold."));
        questionsList.add(new Questions(
                "Why is the disease being called coronavirus disease 2019, COVID-19?",
                "On February 11, 2020 the World Health Organization announced an official name for the disease " +
                        "that is causing the 2019 novel coronavirus outbreak, first identified in Wuhan China. The new " +
                        "name of this disease is coronavirus disease 2019, abbreviated as COVID-19. In COVID-19, ‘CO’ " +
                        "stands for ‘corona,’ ‘VI’ for ‘virus,’ and ‘D’ for disease. Formerly, this disease was referred " +
                        "to as “2019 novel coronavirus” or “2019-nCoV”.\n" +
                        "There are many types of human coronaviruses including some that commonly cause mild "+
                        "upper-respiratory tract illnesses. COVID-19 is a new disease, caused by a novel (or new) " +
                        "coronavirus that has not previously been seen in humans."));
        questionsList.add(new Questions(
                "How does the virus spread?",
                "The virus that causes COVID-19 is thought to spread mainly from person to person, " +
                        "mainly through respiratory droplets produced when an infected person coughs, sneezes, " +
                        "or talks. These droplets can land in the mouths or noses of people who are nearby or" +
                        "possibly be inhaled into the lungs. Spread is more likely when people are in close " +
                        "contact with one another (within about 6 feet).\n" +
                        "COVID-19 seems to be spreading easily and sustainably in the community (“community spread”) in" +
                        " many affected geographic areas. Community spread means people have been infected with the " +
                        "virus in an area, including some who are not sure how or where they became infected."));
        questionsList.add(new Questions(
                "Will warm weather stop the outbreak of COVID-19?",
                "It is not yet known whether weather and temperature affect the spread of COVID-19. " +
                        "Some other viruses, like those that cause the common cold and flu, spread more during " +
                        "cold weather months but that does not mean it is impossible to become sick with these " +
                        "viruses during other months.  There is much more to learn about the transmissibility, " +
                        "severity, and other features associated with COVID-19 and investigations are ongoing."));
        questionsList.add(new Questions(
                "What is community spread?",
                "Community spread means people have been infected with the virus in an area, including "+
                        "some who are not sure how or where they became infected. Each health department determines "+
                        "community spread differently based on local conditions. For information on community spread " +
                        "in your area, please visit your health department’s website."));
        questionsList.add(new Questions(
                "Can mosquitoes or ticks spread the virus that causes COVID-19?",
                "At this time, CDC has no data to suggest that this new coronavirus " +
                        "or other similar coronaviruses are spread by mosquitoes or ticks. " +
                        "The main way that COVID-19 spreads is from person to person. "+
                        "See How Coronavirus Spreads for more information."));
        questionsList.add(new Questions(
                "Can mosquitoes or ticks spread the virus that causes COVID-19?",
                "At this time, CDC has no data to suggest that this new coronavirus " +
                        "or other similar coronaviruses are spread by mosquitoes or ticks. " +
                        "The main way that COVID-19 spreads is from person to person. "+
                        "See How Coronavirus Spreads for more information."));
    }
}