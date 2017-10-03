# CreateViewsAndroid
Рисуем в классе,который наследуется от View,в методе onDraw()
Потом этот класс передаем в setContentView(new MyView(this));

Matrix-хранит матрицу 3*3 для трансформирования координат.Перемещение,преобразование,наклоны,изменения размера

ScaleType.CENTER_CROP-обрезка фото чтобы соответствовать вьюхе,в которую вставляется
RectF-хранит 4 координаты для прямоугольника(лево,верх,право,низ)

Path-позволяет создавать сложные фигуры состоящие из линий,кривых и простых фигур
начальная точка-moveTo().потом-lineTo()


например создаем несколько фигур:
path.reset();
path.addRect(100, 150, 400, 250, Path.Direction.CW);
path.addRect(180, 50, 500, 350, Path.Direction.CW);

//добавляем круг.задаем координаты икс игрек,диаметр и направление
path.addCircle(300, 300, 100, Path.Direction.CW);

//рисуем его
canvas.drawPath(path, paint);

так как в стиле кисти поставлена закраска то закрашено пространство внутри

ставим стиль для кисти:


и другие характеристики


можно написать текст на линии:

paint.setTextSize(50);
canvas.drawTextOnPath("my text on a path",path,0,0,paint);

//рисум Bitmap:
canvas.drawBitmap(getCyanBitmap(), 200, 1000, paint);


голубой прямоугольник из сгенереенной Bitmap:




Также мы може “рисовать рисунком”,используя gjlrkfccs Shader:
инициализируем шейдер с заданной картинкой:
private Shader createShader() {
    bitmap = BitmapFactory.decodeResource(getResources(),
        R.drawable.ic_launcher);
    BitmapShader shader = new BitmapShader(bitmap,
        Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    return shader;
  }


//устанавливаем на кисть и рисуем круг 
paint.setShader(createShader());
 paint.setStyle(Paint.Style.FILL);
canvas.drawCircle(500,1100,300,paint);


кружок низ-центр





ну еще можем создать шейдер с градиентом из нескольких цветов 

private Shader createGradientShader() {
    LinearGradient shader = new LinearGradient(0, 0, 100, 20,
            Color.RED, Color.GREEN, Shader.TileMode.MIRROR);
    return shader;
}

и применнить его:
paint.setShader(createGradientShader());
paint.setStyle(Paint.Style.STROKE);
paint.setStrokeWidth(20);
canvas.drawCircle(200,1200,200,paint);

кружок лево-низ


