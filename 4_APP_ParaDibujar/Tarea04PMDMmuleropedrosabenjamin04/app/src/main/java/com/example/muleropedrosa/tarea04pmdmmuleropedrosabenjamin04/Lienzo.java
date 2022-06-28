package com.example.muleropedrosa.tarea04pmdmmuleropedrosabenjamin04;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;



public class Lienzo extends View {

    //Variable Path para definir como será el trazo al dibujar
    private Path defineTrazo;

    //Tres variables Paint (como pintar) para el canvas (lienzo) y pintar círculo.
    public Paint comoPintarDraw, comoPintarCanvas, circulo;

    //Variable del lienzo (Canvas) donde se dibujará
    private Canvas lienzo;

    //Dos variables de mapas bits, uno para utilizar el pincel general y el otro para usar un pincel de la cara y otro para la estrella
    private Bitmap mapaBit, pincelMapaBit;

    //Tres variables int para los colores amarillo, rojo, azul que corresponde a los colores que se pueden seleccionar.
    // Una variable int para el color negro, que será el color de pintura de inicio.
    //Los cuatro colores se optienen a través de los recursos.
    private int colorAmarillo = getResources().getColor(R.color.amarillo);
    private int colorAzul = getResources().getColor(R.color.azul);
    private int colorRojo = getResources().getColor(R.color.rojo_dibuja);
    private int colorNegro = getResources().getColor(R.color.negro);

    //Constructor que crea el Lienzo con la configuración del lienzo
    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);

        configuracionPintar();
    }

    //Configuración de como pintar
    public void configuracionPintar (){
        //Instancio las variables defineTrazo (path para el trazo), comoPintarDraw (paint para definir como pintar)
        defineTrazo = new Path();
        comoPintarDraw = new Paint();

        //Instancio otro objeto paint que me servirá para crear un primer círculo al iniciar con un click la pintura,
        //de esta forma consigo la sensación de pulsar y pintar con los colores amarillo, azul y rojo.
        circulo = new Paint();

        //SE ESTABLECE DETERMINADAS PROPIEDADES DE PAINT (Como pintar)
        comoPintarDraw.setColor(colorNegro);//Color inicial al pintar

        comoPintarDraw.setAntiAlias(true);//Suaviza los bordes de lo que se está dibujando, pero no tiene impacto en el interior de la forma

        comoPintarDraw.setStrokeWidth(20);//Se establece el ancho de la línea

        comoPintarDraw.setStyle(Paint.Style.STROKE);//Se establece el estilo. El Estilo especifica si el primitivo que se dibuja está relleno, trazado o ambos (en el mismo color). El valor predeterminado es LLENAR.Tiene tres tipos específicos: FILL, STROKE y FILL_AND_STROKE.
        // FILL es el modo de relleno
        // STROKE es el modo de dibujo lineal (es decir, el modo de borde)
        // FILL_AND_STROKE se usa junto con dos modos: dibujar líneas y rellenar. Su valor predeterminado es FILL, modo de relleno.

        comoPintarDraw.setStrokeJoin(Paint.Join.ROUND);//SetStrokeJoin
        // permite establecer tres modos de cómo se representa una unión de línea de líneas gruesas.
        // ROUND significa que se usa una sección circular para rodear la torcedura, BEVEL
        // significa que se usa otro segmento recto corto perpendicular al ángulo de la mitad, y MITER
        // significa que el contorno exterior de las dos líneas de unión se alarga hasta que se cruzan.
        // Sin embargo, cuando el ángulo es muy agudo, ese punto puede alejarse mucho, por lo que
        // normalmente se establece un límite que cambia de MITERa BEVEL, si se alcanza cierta nitidez.
        // ste límite está controlado por setStrokeMiter.

        comoPintarDraw.setStrokeCap(Paint.Cap.ROUND);//setStrokeCap (Paint.Cap cap);
        // Cuando el setStyle of Paint es STROKE (anterior) o FILL_OR_STROKE,
        // establece el estilo de la tapa de línea del pincel, los valores son:Cap.BUTT、Cap.ROUND(Estilo circular)
        // yCap.SQUARE(Estilo cuadrado).


        comoPintarCanvas = new Paint(Paint.DITHER_FLAG);//Se intancia la variable paint del Canvas


    }

    //Creo un método para cambiar la imagen-pincel de "pincelMapaBit"
    public void setPincelMapaBit (int id){
        pincelMapaBit = BitmapFactory.decodeResource(getResources(), id);
    }

    //Creo un método para para borrar el lienzo (canvas) aplicando un nuevo color de fondo.
    public void setBorrar (int color){
        lienzo.drawColor(color);

    }


    //Tamaño asignado a la vista
   @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

       mapaBit = mapaBit.createBitmap(w, h, Bitmap.Config.ARGB_8888);
       lienzo = new Canvas(mapaBit);
    }


    //Pinta la vista. Será llamado desde el OnTouchEvent
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mapaBit, 0, 0,  comoPintarCanvas);
       canvas.drawPath(defineTrazo, comoPintarDraw);

    }


    //Registra los touch de usuario. Establezco dos touch distintos, uno para dibujar con un pincel con path (defineTrazo)
    // y el otro, utilizando un pincel de mapa bits con una imagen que se pintará en el lienzo (canvas) de forma continua
    // (con el registro de touch), dando la sensación de pintar
    //La opción de pintar con un touch u otro, dependerá del color utilizado en el paint "comoPintarDraw".
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        circulo.setStyle(Paint.Style.FILL);

        if (comoPintarDraw.getColor()==colorNegro||
        comoPintarDraw.getColor()==colorAmarillo||
        comoPintarDraw.getColor()==colorAzul||
        comoPintarDraw.getColor()==colorRojo) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_BUTTON_PRESS:
                    //Se crea un circulo para dar la sensación de pintar al primer click.
                    lienzo.drawCircle (touchX, touchY, 10, circulo);
                    break;
                case MotionEvent.ACTION_DOWN:
                    defineTrazo.moveTo(touchX, touchY);
                    lienzo.drawCircle (touchX, touchY, 10, circulo);
                    break;
                case MotionEvent.ACTION_MOVE:
                    defineTrazo.lineTo(touchX, touchY);
                    break;
                case MotionEvent.ACTION_UP:
                    lienzo.drawPath(defineTrazo, comoPintarDraw);
                    defineTrazo.reset();
                    break;
                default:
                    return false;
            }

            //repintar
            invalidate();

        }else{

                switch (event.getAction()) {
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        lienzo.drawBitmap(pincelMapaBit,touchX, touchY,comoPintarDraw);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        lienzo.drawBitmap(pincelMapaBit,touchX, touchY,comoPintarDraw);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        lienzo.drawBitmap(pincelMapaBit,touchX, touchY,comoPintarDraw);

                        break;
                    case MotionEvent.ACTION_UP:
                        lienzo.drawBitmap(pincelMapaBit,touchX, touchY,comoPintarDraw);

                        defineTrazo.reset();
                        break;
                    default:
                        return false;
                }

                //repintar
                invalidate();

            }
        return true;}
    }






