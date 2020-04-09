package top.rregio.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    String peso;
    String altura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Criando o botão e adicionando eventlistener
        Button calcula = findViewById(R.id.CalcularBtn);

        //pegando os textviews
        //pegaDados();
        final TextView tvPeso = findViewById(R.id.Peso);
        final TextView tvAltura = findViewById(R.id.TFAltura);
        final TextView tvRes = findViewById(R.id.lblRes);

        calcula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                peso = tvPeso.getText().toString();
                peso = peso.replaceAll(",", ".");
                altura = tvAltura.getText().toString();
                altura = altura.replaceAll(",", ".");
                if (peso.equals("") && altura.equals("")) {
                } else {
                    double pes = Double.parseDouble(peso);
                    double alt = Double.parseDouble(altura);
                    double calculo = pes / (alt * alt);
                    String imc;
                    DecimalFormat df = new DecimalFormat("#0.00");
                    imc = df.format(calculo);
                    StringBuilder sb = new StringBuilder().append("Resultado:\nPeso: ")
                            .append(pes)
                            .append("\nAltura: ")
                            .append(alt)
                            .append("\nIMC: ")
                            .append(imc);
                    //.append("\n\n#RREGIO_DEV");
                    tvRes.setText(calculaDiagnostico(sb.toString(), calculo));
                }
            }
        });
        final Button shareBtn = findViewById(R.id.share);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, tvRes.getText().toString());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
    }

    String calculaDiagnostico(String texto, double imc) {
        if (imc < 17) {
            texto = texto + "\n\nVocê está muito abaixo do peso!";
        } else if ((imc >= 17) && (imc < 18.5)) {
            texto = texto + "\n\nVocê está abaixo do peso!";
        } else if ((imc >= 18.5) && (imc < 25)) {
            texto = texto + "\n\nVocê está com seu peso normal.\nParabéns por isso!";
        } else if ((imc >= 25) && (imc < 30)) {
            texto = texto + "\n\nVocê está um pouco acima do peso!\nEstá bom, mas se diminuir fica melhor!";
        } else if ((imc >= 30) && (imc < 35)) {
            texto = texto + "\n\nVocê está acima do peso!\nObesidade I";
        } else if ((imc >= 35) && (imc < 40)) {
            texto = texto + "\n\nVocê está muito acima do peso!\nObesidade II";
        } else {
            texto = texto + "\n\nSeu IMC ultrapassa meus limites!\nObesidade III\n#RREGIO_DEV";
        }
        return texto;
    }

    public void abreSobre(View view) {
        Intent sobreIntent = new Intent();
        startActivity(sobreIntent);
    }
}