package com.frsvarsmakten.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.UUID;

/**
 * Created by T420S on 2014-04-11.
 */
public class Drawing extends Fragment {

    private drawingView drawView;
    private float smallBrush, mediumBrush, largeBrush;
    private ImageButton currPaint, drawBtn, eraseBtn, newBtn, saveBtn, firstPaint, secondPaint, thirdPaint,
            fourthPaint, fifthPaint, sixthPaint, seventhPaint, eightPaint,ninthPaint,tenthPaint,
            eleventhPaint, twelvethPaint;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.paint, container, false);


        drawView = (drawingView)rootView.findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout)rootView.findViewById(R.id.paint_colors);
        LinearLayout paintLayout2 = (LinearLayout)rootView.findViewById(R.id.paint_colors2);
        currPaint = (ImageButton)paintLayout.getChildAt(0);
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

        firstPaint = (ImageButton)paintLayout.getChildAt(0);
        secondPaint = (ImageButton)paintLayout.getChildAt(1);
        thirdPaint = (ImageButton)paintLayout.getChildAt(2);
        fourthPaint = (ImageButton)paintLayout.getChildAt(3);
        fifthPaint = (ImageButton)paintLayout.getChildAt(4);
        sixthPaint = (ImageButton)paintLayout.getChildAt(5);
        seventhPaint = (ImageButton)paintLayout2.getChildAt(0);
        eightPaint = (ImageButton)paintLayout2.getChildAt(1);
        ninthPaint = (ImageButton)paintLayout2.getChildAt(2);
        tenthPaint = (ImageButton)paintLayout2.getChildAt(3);
        eleventhPaint = (ImageButton)paintLayout2.getChildAt(4);
        twelvethPaint = (ImageButton)paintLayout2.getChildAt(5);



        firstPaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(view!=currPaint){
                    //update color
        /*Detta kanske ska vara före if-satsen!!!!!! */

                    drawView.setErase(false);
                    drawView.setBrushSize(drawView.getLastBrushSize());
        /*Slut på kanske före if-sats*/

                    ImageButton imgView = (ImageButton)view;
                    String color = view.getTag().toString();
                    drawView.setColor(color);
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
                    currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
                    currPaint=(ImageButton)view;


                }

            }
        });

        secondPaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(view!=currPaint){
                    //update color
        /*Detta kanske ska vara före if-satsen!!!!!! */

                    drawView.setErase(false);
                    drawView.setBrushSize(drawView.getLastBrushSize());
        /*Slut på kanske före if-sats*/

                    ImageButton imgView = (ImageButton)view;
                    String color = view.getTag().toString();
                    drawView.setColor(color);
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
                    currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
                    currPaint=(ImageButton)view;


                }

            }
        });

        thirdPaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(view!=currPaint){
                    //update color
        /*Detta kanske ska vara före if-satsen!!!!!! */

                    drawView.setErase(false);
                    drawView.setBrushSize(drawView.getLastBrushSize());
        /*Slut på kanske före if-sats*/

                    ImageButton imgView = (ImageButton)view;
                    String color = view.getTag().toString();
                    drawView.setColor(color);
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
                    currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
                    currPaint=(ImageButton)view;


                }

            }
        });

        fourthPaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(view!=currPaint){
                    //update color
        /*Detta kanske ska vara före if-satsen!!!!!! */

                    drawView.setErase(false);
                    drawView.setBrushSize(drawView.getLastBrushSize());
        /*Slut på kanske före if-sats*/

                    ImageButton imgView = (ImageButton)view;
                    String color = view.getTag().toString();
                    drawView.setColor(color);
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
                    currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
                    currPaint=(ImageButton)view;


                }

            }
        });

        fifthPaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(view!=currPaint){
                    //update color
        /*Detta kanske ska vara före if-satsen!!!!!! */

                    drawView.setErase(false);
                    drawView.setBrushSize(drawView.getLastBrushSize());
        /*Slut på kanske före if-sats*/

                    ImageButton imgView = (ImageButton)view;
                    String color = view.getTag().toString();
                    drawView.setColor(color);
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
                    currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
                    currPaint=(ImageButton)view;


                }

            }
        });

        sixthPaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(view!=currPaint){
                    //update color
        /*Detta kanske ska vara före if-satsen!!!!!! */

                    drawView.setErase(false);
                    drawView.setBrushSize(drawView.getLastBrushSize());
        /*Slut på kanske före if-sats*/

                    ImageButton imgView = (ImageButton)view;
                    String color = view.getTag().toString();
                    drawView.setColor(color);
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
                    currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
                    currPaint=(ImageButton)view;


                }

            }
        });
        seventhPaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(view!=currPaint){
                    //update color
        /*Detta kanske ska vara före if-satsen!!!!!! */

                    drawView.setErase(false);
                    drawView.setBrushSize(drawView.getLastBrushSize());
        /*Slut på kanske före if-sats*/

                    ImageButton imgView = (ImageButton)view;
                    String color = view.getTag().toString();
                    drawView.setColor(color);
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
                    currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
                    currPaint=(ImageButton)view;


                }

            }
        });

        eightPaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(view!=currPaint){
                    //update color
        /*Detta kanske ska vara före if-satsen!!!!!! */

                    drawView.setErase(false);
                    drawView.setBrushSize(drawView.getLastBrushSize());
        /*Slut på kanske före if-sats*/

                    ImageButton imgView = (ImageButton)view;
                    String color = view.getTag().toString();
                    drawView.setColor(color);
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
                    currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
                    currPaint=(ImageButton)view;


                }

            }
        });

        ninthPaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(view!=currPaint){
                    //update color
        /*Detta kanske ska vara före if-satsen!!!!!! */

                    drawView.setErase(false);
                    drawView.setBrushSize(drawView.getLastBrushSize());
        /*Slut på kanske före if-sats*/

                    ImageButton imgView = (ImageButton)view;
                    String color = view.getTag().toString();
                    drawView.setColor(color);
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
                    currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
                    currPaint=(ImageButton)view;


                }

            }
        });

        tenthPaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(view!=currPaint){
                    //update color
        /*Detta kanske ska vara före if-satsen!!!!!! */

                    drawView.setErase(false);
                    drawView.setBrushSize(drawView.getLastBrushSize());
        /*Slut på kanske före if-sats*/

                    ImageButton imgView = (ImageButton)view;
                    String color = view.getTag().toString();
                    drawView.setColor(color);
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
                    currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
                    currPaint=(ImageButton)view;


                }

            }
        });

        eleventhPaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(view!=currPaint){
                    //update color
        /*Detta kanske ska vara före if-satsen!!!!!! */

                    drawView.setErase(false);
                    drawView.setBrushSize(drawView.getLastBrushSize());
        /*Slut på kanske före if-sats*/

                    ImageButton imgView = (ImageButton)view;
                    String color = view.getTag().toString();
                    drawView.setColor(color);
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
                    currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
                    currPaint=(ImageButton)view;


                }

            }
        });

        twelvethPaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(view!=currPaint){
                    //update color
        /*Detta kanske ska vara före if-satsen!!!!!! */

                    drawView.setErase(false);
                    drawView.setBrushSize(drawView.getLastBrushSize());
        /*Slut på kanske före if-sats*/

                    ImageButton imgView = (ImageButton)view;
                    String color = view.getTag().toString();
                    drawView.setColor(color);
                    imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
                    currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
                    currPaint=(ImageButton)view;


                }

            }
        });








        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);


        

        /*

        getcontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new GetContacts().execute();
            }
        });
         */


        saveBtn = (ImageButton)rootView.findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //save drawing
                AlertDialog.Builder saveDialog = new AlertDialog.Builder(getActivity());

                saveDialog.setTitle("Save drawing");
                saveDialog.setMessage("Save drawing to device Gallery?");
                saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        //save drawing
                        drawView.setDrawingCacheEnabled(true);
                        String imgSaved = MediaStore.Images.Media.insertImage(
                                getActivity().getContentResolver(), drawView.getDrawingCache(),
                                UUID.randomUUID().toString()+".png", "drawing");
                        if(imgSaved!=null){
                            Toast savedToast = Toast.makeText(getActivity().getApplicationContext(),
                                    "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                            savedToast.show();
                        }
                        else{
                            Toast unsavedToast = Toast.makeText(getActivity().getApplicationContext(),
                                    "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                            unsavedToast.show();
                        }
                        drawView.destroyDrawingCache();

                    }
                });
                saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                saveDialog.show();

            }
        });


        drawBtn = (ImageButton)rootView.findViewById(R.id.draw_btn);

        drawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog brushDialog = new Dialog(getActivity());
                brushDialog.setTitle("Brush size:");
                brushDialog.setContentView(R.layout.brush_chooser);

                ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);
                smallBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawView.setBrushSize(smallBrush);
                        drawView.setLastBrushSize(smallBrush);
                        drawView.setErase(false);
                        brushDialog.dismiss();
                    }
                });

                ImageButton mediumBtn = (ImageButton) brushDialog.findViewById(R.id.medium_brush);
                mediumBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawView.setBrushSize(mediumBrush);
                        drawView.setLastBrushSize(mediumBrush);
                        drawView.setErase(false);
                        brushDialog.dismiss();
                    }
                });

                ImageButton largeBtn = (ImageButton) brushDialog.findViewById(R.id.large_brush);
                largeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawView.setBrushSize(largeBrush);
                        drawView.setLastBrushSize(largeBrush);
                        drawView.setErase(false);
                        brushDialog.dismiss();
                    }
                });

                brushDialog.show();
            }
        });



        drawView.setBrushSize(mediumBrush);

        eraseBtn = (ImageButton)rootView.findViewById(R.id.erase_btn);
        eraseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//switch to erase - choose size

                final Dialog brushDialog = new Dialog(getActivity());
                brushDialog.setTitle("Eraser size:");
                brushDialog.setContentView(R.layout.brush_chooser);



                ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
                smallBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        drawView.setErase(true);
                        drawView.setBrushSize(smallBrush);
                        brushDialog.dismiss();
                    }
                });
                ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
                mediumBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        drawView.setErase(true);
                        drawView.setBrushSize(mediumBrush);
                        brushDialog.dismiss();
                    }
                });
                ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
                largeBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        drawView.setErase(true);
                        drawView.setBrushSize(largeBrush);
                        brushDialog.dismiss();
                    }
                });


                brushDialog.show();}});


        newBtn = (ImageButton)rootView.findViewById(R.id.new_btn);
        newBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //new button
            /*Detta ska kanske vara i onCreate istället*/

                        AlertDialog.Builder newDialog = new AlertDialog.Builder(getActivity());
                        newDialog.setTitle("New drawing");
                        newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
                        newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                drawView.startNew();
                                dialog.dismiss();
                            }
                        });
                        newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                dialog.cancel();
                            }
                        });
                        newDialog.show();
            /*Slut på kanske i onCreate*/

                    }
                });





        //RETURNERA NÅGOT HÄR
        return rootView;

    }

/*Detta är från rita */



    public void paintClicked(View view){
        //use chosen color


        if(view!=currPaint){
            //update color
        /*Detta kanske ska vara före if-satsen!!!!!! */

            drawView.setErase(false);
            drawView.setBrushSize(drawView.getLastBrushSize());
        /*Slut på kanske före if-sats*/

            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            drawView.setColor(color);
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currPaint=(ImageButton)view;


        }
    }

/* Detta är från rita */



    //HÄR SKA DEN EGENTLIGEN OVERRIDE - MEN DET GICK INTE
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mInflater = getActivity().getMenuInflater();
        // Inflate the menu; this adds items to the action bar if it is present.
        mInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}