package com.example.pizzeria;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    private List<Pizza> pizzaList;
    private final TotalUpdateListener totalUpdateListener;

    public PizzaAdapter(List<Pizza> pizzaList, TotalUpdateListener totalUpdateListener) {
        this.pizzaList = pizzaList;
        this.totalUpdateListener = totalUpdateListener;
    }

    @Override
    public PizzaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.pizzaName.setText(pizza.getName());
        holder.pizzaImage.setImageResource(pizza.getImageResId());

        // Manipular o aumento e diminuição da quantidade
        holder.buttonIncrease.setOnClickListener(v -> {
            int quantity = Integer.parseInt(holder.pizzaQuantity.getText().toString());
            quantity++;
            holder.pizzaQuantity.setText(String.valueOf(quantity));
            totalUpdateListener.onUpdate(quantity);
        });

        holder.buttonDecrease.setOnClickListener(v -> {
            int quantity = Integer.parseInt(holder.pizzaQuantity.getText().toString());
            if (quantity > 0) {
                quantity--;
                holder.pizzaQuantity.setText(String.valueOf(quantity));
                totalUpdateListener.onUpdate(-1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {

        public TextView pizzaName;
        public ImageView pizzaImage;
        public TextView pizzaQuantity;
        public Button buttonIncrease;
        public Button buttonDecrease;

        public PizzaViewHolder(View itemView) {
            super(itemView);
            pizzaName = itemView.findViewById(R.id.pizza_name);
            pizzaImage = itemView.findViewById(R.id.pizza_image);
            pizzaQuantity = itemView.findViewById(R.id.pizza_quantity);
            buttonIncrease = itemView.findViewById(R.id.button_increase);
            buttonDecrease = itemView.findViewById(R.id.button_decrease);
        }
    }

    // Interface para atualizar o total
    public interface TotalUpdateListener {
        void onUpdate(int quantity);
    }
}

