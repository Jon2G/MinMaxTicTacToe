package com.example.minmaxgato;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minmaxgato.abstractions.Box;
import com.example.minmaxgato.abstractions.Gato;
import com.example.minmaxgato.enums.AIResult;
import com.example.minmaxgato.enums.Player;

public class MainActivity extends AppCompatActivity {

    private GridLayout MainGrid;
    private com.example.minmaxgato.abstractions.Gato Gato;
    private TextView LabelTitle;
    private Button AgainButton;
    private boolean HasBeenDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainGrid = findViewById(R.id.MainGrid);
        LabelTitle=findViewById(R.id.label_title);
        MainGrid.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> DrawGameBoard());
        AgainButton=findViewById(R.id.again_button);
        AgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HasBeenDraw=false;
                DrawGameBoard();
            }
        });
    }
    private void  UpdateBoard(AIResult result){
        boolean IsHuman=Gato.Human==Gato.CurrentPlayer;
        LabelTitle.setText(IsHuman? "¡Es tu turno!":"Espera...");
        for (int r=0;r<3;r++) {
            for (int c = 0; c < 3; c++) {
                int Id = Integer.parseInt(r + "" + c);
                View childView=MainGrid.findViewById(Id);
                ImageView imageView= childView.findViewById(R.id.imageView);
                SetPlayer(imageView,Gato.Board.Get(r,c).player);
            }
        }
        AlertDialog.Builder   builder = new AlertDialog.Builder(this);
        switch (result){
            case TIE:
                builder.setMessage("Nadie gana este juego").setTitle("Empate");
                break;
            case DONE:
                return;
            case I_WIN:
                builder.setMessage("Mejor \"suerte\" para la proxima.").setTitle("Yo gano este juego ;)");
                break;
            case I_FAIL:
                builder.setMessage("No es posbile que me hayas derrotado.").setTitle("Tramposo");
                break;
        }
        LabelTitle.setText("Mejor \"suerte\" para la proxima.");
        Gato.CurrentPlayer=Player.NONE;
        builder.setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AgainButton.setVisibility(View.VISIBLE);
            }
        });
        builder.create().show();
    }
    private void DrawGameBoard(){
        if(HasBeenDraw){
            return;
        }
        HasBeenDraw=true;
        AgainButton.setVisibility(View.INVISIBLE);
        Gato=new Gato();
        MainGrid.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        MainGrid.setColumnCount(3);
        MainGrid.setRowCount(3);
        MainGrid.removeAllViews();
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int r=0;r<3;r++){
            for (int c=0;c<3;c++){
                //Load layout xml of child layout you want to add
                int Id=Integer.parseInt(r+""+c);
                View childView= inflater.inflate(R.layout.casilla_fragment, null);
                CardView cardView= childView.findViewById(R.id.box_card_view);
                final int row=r;
                final int col=c;
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Gato.CurrentPlayer==Gato.Human) {
                            UpdateBoard(Gato.MarkBox(row,col,Gato.CurrentPlayer));
                        }
                }});

                childView.setId(Id);
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.setMargins(3,3,3,3);
                layoutParams.columnSpec=GridLayout.spec(c,1);
                layoutParams.rowSpec=GridLayout.spec(r,1);

                layoutParams.width=(MainGrid.getMeasuredWidth()-3)/3;
                layoutParams.height=layoutParams.width;

                childView.setLayoutParams(layoutParams);
                MainGrid.addView(childView);
                childView.requestLayout();

            }
        }
        UpdateBoard(AIResult.DONE);
    }
    private void SetPlayer(ImageView imageView,Player player){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            switch (player){
                case X:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.x, getApplicationContext().getTheme()));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    break;
                case O:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.o, getApplicationContext().getTheme()));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    break;
                default:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.clear, getApplicationContext().getTheme()));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    break;
            }
        } else {
            switch (player){
                case X:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.x));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    break;
                case O:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.o));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    break;
                default:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.clear));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    break;
            }
        }
    }
}