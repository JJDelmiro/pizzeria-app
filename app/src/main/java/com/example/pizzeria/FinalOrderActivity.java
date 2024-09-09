package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FinalOrderActivity extends AppCompatActivity {

    private TextView orderTotal;
    private Button buttonFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_order); // Certifique-se que o arquivo XML final_order existe e está correto.

        // Recupera o valor total passado pela MainActivity
        double totalValue = getIntent().getDoubleExtra("TOTAL_VALUE", 0);

        // Inicializa os componentes
        orderTotal = findViewById(R.id.order_total);
        buttonFinish = findViewById(R.id.button_finish);

        // Define o valor total no TextView
        orderTotal.setText(String.format("Total: R$%.2f", totalValue));

        // Ao clicar no botão, volta para a MainActivity
        buttonFinish.setOnClickListener(v -> {
            // Você pode fechar o app ou voltar para a MainActivity
            Intent intent = new Intent(FinalOrderActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
