package com.example.codeandcoffee.ui.menu;

import android.content.Intent;
import android.os.Bundle;

import com.example.codeandcoffee.OrderActivity;
import com.example.codeandcoffee.PickupActivity;
import com.example.codeandcoffee.adapter.MenuAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.codeandcoffee.R;
import com.example.codeandcoffee.object.CoffeeMenuItem;
import com.example.codeandcoffee.object.OrderMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {
    public List<CoffeeMenuItem> coffeeMenu = new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public List<OrderMenu> menuList = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button buttonCart;
    private TextView tvCount;
    private TextView tvTotal;
    public void updateItemCount() {
        if (tvCount != null) {
            tvCount.setText(String.valueOf(PickupActivity.MenuCart.size()) + " items");
            double i = 0;
            for (OrderMenu e : PickupActivity.MenuCart) {
                double price = e.getPrice();
                if(e.getWhippedCreame())
                    price+=1.20;
                if(e.getCaramelDrizzled())
                    price+=1;
                if(e.getChocolateDrizzled())
                    price+=1;
                i+= (price*e.getQuantity());
            }
            tvTotal.setText(String.format("RM %.2f",i));
        }

    }
    public MenuFragment() {
        // Required empty public constructor
        updateItemCount();
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
        updateItemCount();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        buttonCart = view.findViewById(R.id.btn_cart);
        tvCount = view.findViewById(R.id.items_count);
        tvTotal = view.findViewById(R.id.cart_price);
        updateItemCount();

        if(PickupActivity.MenuCart.isEmpty()){
            buttonCart.setBackgroundTintList(getResources().getColorStateList(R.color.diselect));
        }
        else {
            buttonCart.setBackgroundTintList(getResources().getColorStateList(R.color.select));
            buttonCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PickupActivity.class);
                    //intent.putParcelableArrayListExtra("orderMenuList", new ArrayList<>(menuList));
                    v.getContext().startActivity(intent);

                }
            });
        }

        generateCoffeeMenu();

        RecyclerView hotRecyclerView = view.findViewById(R.id.recycler_menu);
        GridLayoutManager hotGridLayoutManager = new GridLayoutManager(getContext(), 2);
        hotRecyclerView.setLayoutManager(hotGridLayoutManager);

        List<CoffeeMenuItem> hotCoffeeMenu = getHotCoffeeMenu();
        MenuAdapter hotMenuAdapter = new MenuAdapter(hotCoffeeMenu, getContext());
        hotRecyclerView.setAdapter(hotMenuAdapter);

        RecyclerView icedRecyclerView = view.findViewById(R.id.recycler_menuiced);
        GridLayoutManager icedGridLayoutManager = new GridLayoutManager(getContext(), 2);
        icedRecyclerView.setLayoutManager(icedGridLayoutManager);

        List<CoffeeMenuItem> icedCoffeeMenu = getIcedCoffeeMenu();
        MenuAdapter icedMenuAdapter = new MenuAdapter(icedCoffeeMenu, getContext());
        icedRecyclerView.setAdapter(icedMenuAdapter);

        return view;
    }

    private void generateCoffeeMenu() {

        // Clear the menuList
        coffeeMenu.clear();
        // Sample coffee menu items with image resource IDs
        coffeeMenu.add(new CoffeeMenuItem("Espresso", 2.99, R.drawable.espressocoffee,
                "Espresso is a concentrated coffee served in small shots, made by forcing hot water through finely-ground coffee beans.","Hot"));
        coffeeMenu.add(new CoffeeMenuItem("Latte", 3.49, R.drawable.lattecoffee,
                "A latte is a coffee beverage consisting of espresso and steamed milk, topped with frothed milk for a creamy texture.","Hot"));
        coffeeMenu.add(new CoffeeMenuItem("Cappuccino", 3.99, R.drawable.cappuccinocoffee,
                "Cappuccino is a balanced blend of espresso, steamed milk, and frothed milk, creating a rich and creamy coffee experience.","Hot"));
        coffeeMenu.add(new CoffeeMenuItem("Americano", 3.29, R.drawable.americanocoffee,
                "An Americano is a diluted espresso, made by adding hot water to a shot of espresso, resulting in a coffee with a strength between espresso and drip coffee.","Hot"));

        coffeeMenu.add(new CoffeeMenuItem("Iced Americano", 4.99, R.drawable.icedamericano,
                "Pure, strong, and refreshing. Our Iced Americano is a double shot of espresso poured over ice and lightly diluted with cold water.", "Iced"));
        coffeeMenu.add(new CoffeeMenuItem("Iced Caramel Macchiato", 5.29, R.drawable.icedcaramelmacchiato,
                "Layers of cold milk, vanilla syrup, and espresso over ice, finished with a drizzle of caramel – a balanced and delightful iced macchiato.", "Iced"));
        coffeeMenu.add(new CoffeeMenuItem("Hazelnut Haze Iced Latte", 6.29, R.drawable.hazelnuticedlatte,
                "A delightful iced latte featuring hazelnut flavor, creating a nutty and aromatic coffee experience.", "Iced"));
        coffeeMenu.add(new CoffeeMenuItem("Espresso Over Ice", 4.49, R.drawable.espressooverice,
                "A simple yet bold choice – a shot of espresso poured over ice, providing a strong and invigorating iced coffee experience.", "Iced"));
    }

    private List<CoffeeMenuItem> getHotCoffeeMenu() {
        List<CoffeeMenuItem> hotCoffeeMenu = new ArrayList<>();
        for (CoffeeMenuItem item : coffeeMenu) {
            if ("Hot".equals(item.getCategory())) {
                hotCoffeeMenu.add(item);
            }
        }
        return hotCoffeeMenu;
    }

    private List<CoffeeMenuItem> getIcedCoffeeMenu() {
        List<CoffeeMenuItem> icedCoffeeMenu = new ArrayList<>();
        for (CoffeeMenuItem item : coffeeMenu) {
            if ("Iced".equals(item.getCategory())) {
                icedCoffeeMenu.add(item);
            }
        }
        return icedCoffeeMenu;
    }
}