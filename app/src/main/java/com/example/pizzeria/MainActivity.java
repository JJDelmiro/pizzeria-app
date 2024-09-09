package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PizzaAdapter pizzaAdapter;
    private List<Pizza> pizzaList;
    private TextView totalSummary;
    private Button concludeOrderButton;

    // Variáveis para o total de pizzas e o valor final
    private int totalQuantity = 0;
    private double totalValue = 0.0;
    private static final double PIZZA_PRICE = 30.00;  // Preço padrão de cada pizza

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view_pizzas);
        totalSummary = findViewById(R.id.total_summary);
        concludeOrderButton = findViewById(R.id.btn_conclude_order);

        // Configurando o layout em grade com duas colunas
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Instanciando o Database
        Database database = new Database();

        // Criando a lista de pizzas com base no Database
        pizzaList = new ArrayList<>();
        for (int i = 0; i < database.pizzas.length; i++) {
            pizzaList.add(new Pizza(database.answer[i], database.pizzas[i]));
        }

        // Configurando o adaptador
        pizzaAdapter = new PizzaAdapter(pizzaList, this::updateTotal);
        recyclerView.setAdapter(pizzaAdapter);

        // Configurando o botão de concluir pedido
        concludeOrderButton.setOnClickListener(v -> {
            // Ao clicar em "Concluir Pedido", navega para a tela final
            Intent intent = new Intent(MainActivity.this, FinalOrderActivity.class);
            intent.putExtra("TOTAL_VALUE", totalValue);  // Passa o valor total para a tela final
            startActivity(intent);
            finish();
        });
    }

    // Função para atualizar a quantidade total de pizzas e o valor final
    private void updateTotal(int quantity) {
        totalQuantity += quantity;
        totalValue = totalQuantity * PIZZA_PRICE;

        totalSummary.setText("Quantidade de pizzas: " + totalQuantity + " | Valor Total: R$ " + String.format("%.2f", totalValue));
    }
}
