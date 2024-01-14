package com.example.codeandcoffee.ui.menu;

import android.os.Bundle;

import com.example.codeandcoffee.adapter.MenuAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.codeandcoffee.R;
import com.example.codeandcoffee.object.CoffeeMenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_menu);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        List<CoffeeMenuItem> allcoffeeMenu = generateCoffeeMenu();
        MenuAdapter menuadapter = new MenuAdapter(allcoffeeMenu, getContext());
        recyclerView.setAdapter(menuadapter);

        return view;
    }

    private List<CoffeeMenuItem> generateCoffeeMenu() {
        List<CoffeeMenuItem> coffeeMenu = new ArrayList<>();

        // Sample coffee menu items with image resource IDs
        coffeeMenu.add(new CoffeeMenuItem("Espresso", 2.99, R.drawable.espressocoffee));
        coffeeMenu.add(new CoffeeMenuItem("Latte", 4.49, R.drawable.lattecoffee));
        coffeeMenu.add(new CoffeeMenuItem("Cappuccino", 3.99, R.drawable.cappuccinocoffee));
        coffeeMenu.add(new CoffeeMenuItem("Americano", 3.29, R.drawable.americanocoffee));

        // Add more coffee items as needed

        return coffeeMenu;
    }


}