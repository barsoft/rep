{Кутейко Е. ИСПР-А-1;
Лабораторная работа №14
                                Условие:
Оптовая База:Название товара, Количество на складе,
             Стоимость единицы, Название поставщика, Срок Поставки}
Program Main;
uses
    Crt,Printer,Poc;
var
   BD:Rec;
   flag:boolean;
   v:integer;
   F:text;
Begin
     ClrScr;
     Repeat
           ClrScr;
           Writeln('1 - Ввод из файла;   2 - Ввод с консоли;');
           Write('Key: ');
           Readln(v)
     Until (v=1) Or (v=2);
     flag:=true;
     Case v Of
          1:InFile(BD,Size);
          2:InConsole(BD,Size)
     End;
     if flag=False
     then begin
     Delay(32000);
     exit;
     end;
     Repeat
           {ClrScr;}
           flag:=true;
           Writeln('1) Вывод в файл;                            2)  Вывод на консоль;');
           Writeln('3) Вывод анкеты на экран;                   4)  Вывод анкеты в текствый файл;');
           Writeln('5) Присвоение элементу константных значений;6)  Присваивание объектов;');
           Write('7) Сравнение по числовому ключу;            8)  Cравнение по алфавитному ключу; ');
           Writeln('9) Сортировка анкет по заданному ключу;     10) Вставка новой анкеты;');
           Write('11) Удаление анкеты;                        12) Изменение значения поля записи;');
           Writeln(' 13) Поиск строковых параметров анкет;       14) Поиск количество товара; ');
           Writeln('15) Печать всей базы анкет;                 16) Печать одной анкеты;');
           Writeln('17) Переименование файла;                    18) Удаление файла;');
           Writeln('19) Exit.');
           Write('Key: ');
           Readln(v);
     Case v Of
          1:OutFile(BD,Size);
          2:OutConsole(BD,Size);
          3:OutPutElem(BD,Size);
          4:OutPutElemT(BD,Size);
          5:Constanta(BD,Size);
          6:Prisvaivanie(BD,Size);
          7:SravnPoChisl(BD,Size);
          8:SravnAlf(BD,Size);
          9:Sort(BD,Size);
          10:AddElem(BD,Size);
          11:DelElem(BD,Size);
          12:Add(BD,Size);
          13:SearchStr(BD,Size);
          14:SearchWord(Bd,Size);
          15:Print(BD,Size);
          16:PrintOne(BD,Size);
            17:Begin
                  Ran(F,flag);
                  if Flag=FALSE
                     Then
                         Begin
                              deLay(32000);
                              exit;
                         End;
              End;
          18:Begin
                  Del(f,flag);
                  if Flag=FALSE
                     Then
                         Begin
                              deLay(32000);
                              exit;
                         End;
                  End;

     End;
     Until v=19;
End.