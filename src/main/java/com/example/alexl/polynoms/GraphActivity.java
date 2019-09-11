package com.example.alexl.polynoms;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GraphActivity extends AppCompatActivity
{
    private Button draw;
    private EditText input;
    private TextView plot;
    private Polynomial polynomial;
    private GraphView graph;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        input=(EditText)findViewById(R.id.editText);
        input.setSingleLine(true);
        draw=(Button)findViewById(R.id.button);
        graph=(GraphView)findViewById(R.id.graph);
        plot=(TextView)findViewById(R.id.textView);

        draw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try
                {
                    polynomial=Polynomial.parsePolynomial(input.getText().toString()); //TO SHOW THE POLYNOM IN THE P(x) FIELD
                    graph.drawGraph(Polynomial.parsePolynomial(input.getText().toString()));
                    input.setText(""+polynomial.toString()); //AFTER DRAWING SHOW THE DRAWN POLYNOM AS ASKED
                    plot.setText("p(x)="+polynomial.toString());
                }
                catch (MalformedPolynomialException e)
                {
                    if(input.getText().toString().equals(""))
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "No Polynom was found!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                    else
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Not a valid Polynom! Please try again", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });
    }
}
