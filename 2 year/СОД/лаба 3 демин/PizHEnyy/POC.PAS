Unit Poc;

Interface

Uses
    Crt,Printer;
Const
     Max=10;
     MaxWord=65535;
Type Rec=array[1..Max] of record
                                 TovName:string[10];
                                 Kol:integer;
                                 Stoim:integer;
                                 PostName:string[11];
                                 SrokPost:string[7];
                          End;
Var
   Mas:Rec;
   Size:byte;
   Count2:word;
{____________________________________________________________________________}
Procedure InConsole(var Mas:Rec; var Size:byte); {Ввод данных из консоли}
Procedure OutConsole(Mas:Rec;Size:byte);{вывод данных на консоль}
Procedure InFile(var Mas:Rec;var Size:byte);{Ввод данных из файла}
Procedure OutFile(Mas:Rec;Size:byte); {Вывод данных в файл}
Procedure OutPutElem(Mas:Rec;Size:byte);{Вывод значений записи Pos на экран монитора}
Procedure OutPutElemT(Mas:Rec;Size:byte);{форматный вывод значения объекта на терминал}
Procedure Constanta(Var Mas:Rec; Size:byte);{Присвоение элементу константных значений}
Procedure Prisvaivanie(Var Mas:Rec; Size:byte);{Присваивание объектов}
Procedure SravnPoChisl(Mas:Rec;Size:byte);{Сравнение элементов по числовому ключу}
Procedure SravnAlf(Mas:Rec;Size:word);{Сравнение элементов записей массива по алфавитному ключу}
Procedure Sort(var Mas:Rec; Size:byte);{Сортировка записей массива по заданному ключу}
Procedure AddElem(Var Mas:Rec;Var Size:byte);{Вставка элемента в массив записей в позицию Pos}
Procedure DelElem(Var Mas:Rec; Var Size:Byte);{Удаление элемента массива записей с позиции Pos}
Procedure Add(Var Mas:Rec; Size:byte);{изменение значения поля записи}
Procedure SearchStr(Mas:Rec; Size:byte);{Поиск строковых парметров записи}
Procedure SearchWord(Mas:Rec; Size:byte);{Поиск количество товара}
Procedure Print(Mas:Rec; Size:word);{Печать всей базы анкет}
Procedure PrintOne(Mas:Rec; Size:word);{Печать одной анкеты}
procedure Ran(var F:text;var flag:boolean);
procedure Del(var F:text;var Flag:boolean);

{____________________________________________________________________________}

Implementation
{____________________________________________________________________________}

{____________________________________________________________________________}
Procedure InConsole(var Mas:Rec; var Size:byte); {Ввод данных из консоли}
          Var
             i:byte;
Begin
     Repeat
           ClrScr;
           Write('Enter amount of questionnaires (from 1 to ',Max,' ): ');
           {$I-}Readln(Size){$I+}
     Until (Size>=0) And (Size<=Max) And (IoResult=0);
     Writeln('InPut of questionnaires: ');
     For i:=1 To Size Do

              Begin
                   Write('********************************************************************************');
                   Writeln('':28,'Questionnaire №',i);
                   Write('Enter Goods: ');
                   Readln(Mas[i].TovName);
                   Write('Enter Number: ');
                   Readln(Mas[i].Kol);
                   Write('Enter Cost: ');
                   Readln(Mas[i].Stoim);
                   Write('Mas[i].Enter Supplier: ');
                   Readln(Mas[i].PostName);
                   Write('Enter Delivery: ');
                   Readln(Mas[i].SrokPost);

              End;
End;
{____________________________________________________________________________}
Procedure OutConsole(Mas:Rec;Size:byte);{вывод данных на консоль}
          Var
             i:byte;
Begin
     ClrScr;
     Writeln('OutPut of questionnaires: ');
     For i:=1 To Size Do
         With Mas[i] Do
              Begin
                   Writeln('1) Goods: ',TovName);
                   Writeln('2) Number: ',Kol);
                   Writeln('3) Cost: ',Stoim);
                   Writeln('4) Supplier:',PostName);
                   Writeln('5) Delivery: ',SrokPost);
                   Write('********************************************************************************');
              End;
     Write('Press <Enter> to continue: ');
     Readln
End;
{____________________________________________________________________________}
Procedure InFile(var Mas:Rec;var Size:byte);{Ввод данных из файла}
          Var
             Tex:Text;
             i,j:word;
             s,v:string;
             Elem:word;
             code:integer;
Begin
     Assign(Tex,'C:\Input.txt');
     {$I-}Reset(Tex);{$I+}
     If IOReSult<>0
        Then
            Begin
                 Write('File not found!');
                 Exit
            End
        Else
            Begin
                 i:=0;
                 While not EoF(Tex) do
                       Begin
                            Inc(i,1);
                            For j:=1 To 5 Do
                                Begin
                                     If j<>6
                                        Then
                                            Case j of
                                                 1:
                                                   Begin
                                                        Readln(Tex,s);
                                                        v:=Copy(s,11,Length(s));
                                                        Mas[i].TovName:=v
                                                   End;
                                                 2:
                                                   Begin
                                                         Readln(Tex,s);
                                                         v:=Copy(s,12,Length(s));
                                                         Val(v,Elem,code);
                                                         If Code=0
                                                         Then
                                                             Mas[i].Kol:=Elem
                                                   End;
                                                 3:
                                                   Begin
                                                         Readln(Tex,s);
                                                         v:=Copy(s,10,Length(s));
                                                         Val(v,Elem,code);
                                                         If Code=0
                                                         Then

                                                             Mas[i].Stoim:=Elem
                                                   End;
                                                 4:
                                                   Begin
                                                        Readln(Tex,s);
                                                        v:=Copy(s,14,Length(s));
                                                        Mas[i].PostName:=v
                                                   End;
                                                 5:
                                                   Begin
                                                        Readln(Tex,s);
                                                        v:=Copy(s,14,Length(s));
                                                        Mas[i].SrokPost:=v
                                                   End;
                                            End
                                        Else
                                            Begin
                                                       Begin
                                                             Writeln('InPut file error!');
                                                             Exit
                                                        End
                                            End
                                End
                       End
            End;
            Size:=i;
            Close(Tex)
End;
{____________________________________________________________________________}
Procedure OutFile(Mas:Rec;Size:byte); {Вывод данных в файл}
          Var
             Tex:Text;
             i,j:byte;
             Elem:real;
             s:string;
             Ch:char;
Begin
     ClrScr;
     Repeat
           Write('Перезаписать исходный файл - y, отмена - n');
           ch:=ReadKey
     Until (ch='y') Or (Ch='n');
     If Ch='y'
        Then
            Begin
                 Assign(Tex,'C:\Input.txt');
                 ReWrite(Tex);

                 For i:=1 To Size Do
                     Begin
                           For j:=1 To 6 Do
                              Case j of
                                   1:Writeln(Tex,'1) Goods: ',Mas[i].TovName);
                                   2:
                                    Begin
                                          Elem:=Mas[i].Kol;
                                          Str(Elem:0:0,s);
                                          Writeln(Tex,'2) Number: ',s);
                                     End;
                                   3: Begin
                                          Elem:=Mas[i].Stoim;
                                          Str(Elem:0:0,s);
                                          Writeln(Tex,'3) Cots: ',s);
                                     End;
                                   4:Writeln(Tex,'4) Supplier: ',Mas[i].PostName);
                                   5:Writeln(Tex,'5) Delivery: ',Mas[i].srokPost);

                              End
                     End;
                 Close(Tex)
            End
        Else
            {Begin
                 Assign(Tex,'C:\OutPut.txt');
                 ReWrite(Tex);
                    For i:=1 To Size Do
                     Begin
                           For j:=1 To 6 Do
                              Case j of
                                   1:Writeln(Tex,'1) Goods: ',Mas[i].TovName,';');
                                   2:
                                    Begin
                                          Elem:=Mas[i].Kol;
                                          Str(Elem:0:0,s);
                                          Writeln(Tex,'2) Number: ',s);
                                     End;
                                   3: Begin
                                          Elem:=Mas[i].Stoim;
                                          Str(Elem:0:0,s);
                                          Writeln(Tex,'3) Cots',s);
                                     End;
                                   4:Writeln(Tex,'4) Supplier: ',Mas[i].PostName,';');
                                   5:Writeln(Tex,'5) Delivery: ',Mas[i].srokPost,';');

                              End
   End;
                 Close(Tex)
            End;         }
End;
{____________________________________________________________________________}
Procedure OutPutElem(Mas:Rec;Size:byte);{Вывод значений записи Pos на экран монитора}
          Var
             Pos:byte;
Begin
     Repeat
           Write('Enter position for output questionnaire (from 1 to ',Size,' ): ');
           {$I-}Readln(Pos){$I+}
     Until (Pos>0) And (Size<=Size);
     {With Mas[Pos] Do  }
          Begin
               Writeln('1) Goods: ',Mas[pos].TovName);
               Writeln('2) Number: ',Mas[pos].Kol);
               Writeln('3) Cost: ',Mas[pos].Stoim);
               Writeln('4) Supplier: ',Mas[pos].PostName);
               Writeln('5) Delivery: ',Mas[pos].SrokPost);

               Write('********************************************************************************')
          End;
     Write('Press <Enter> to continue: ');
     Readln
End;
{____________________________________________________________________________}
Procedure OutPutElemT(Mas:Rec;Size:byte);{форматный вывод значения объекта на терминал}
          Var
             Tex:Text;
             Pos:byte;
Begin

     Repeat
           Write('Enter position for output questionnaire in file (from 1 to ',Size,' ): ');
           {$I-}Readln(Pos){$I+}
     Until (Pos>0) And (Size<=Size);
     Assign(Tex,'C:\Terminal.txt');
     ReWrite(Tex);
     With Mas[Pos] Do
          Begin
               Writeln(Tex,'1) Goods: ',tovName,';');
               Writeln(Tex,'2) Number: ',Kol,';');
               Writeln(Tex,'3) Cost: ',Stoim,';');
               Writeln(Tex,'4) Supplier: ',PostName,';');
               Writeln(Tex,'5) Delivery: ',SrokPost,';');

          End;
     Close(Tex)
End;
{____________________________________________________________________________}
Procedure Constanta(Var Mas:Rec; Size:byte);{Присвоение элементу константных значений}
          Var
             s:string;
             Elem:word;
             Pos:byte;
Begin
     Repeat
           Write('Enter position of questionnaire for RewRiting (from 1 to ',Size,' ): ');
           {$I-}Readln(Pos){$I+}
     Until (Pos>0) And (Size<=Size);
     With Mas[Pos] Do
          Begin
               Write('Enter Goods: ');
               Readln(s);
               TovName:=s;
               Repeat
                     Write('Enter Number (from 0 to ',MaxWord,' sht ); ');
                     Readln(Elem)
               Until (Elem>0) And (Elem<=MaxWord);
               Kol:=Elem;
               Repeat
                     Write('Enter Cost (from 0 to ',MaxWord,' $ ); ');
                     Readln(Elem)
               Until (Elem>0) And (Elem<=MaxWord);
               Stoim:=Elem;
               Write('Enter Supplier: ');
               Readln(s);
               PostName:=s;
               Write('Enter Delivery: ');
               Readln(s);
               SrokPost:=s;

          End
End;
{____________________________________________________________________________}
Procedure Prisvaivanie(Var Mas:Rec; Size:byte);{Присваивание объектов}
          Var
             Pos1,Pos2:byte;
Begin
     Repeat
           Write('Enter first position: ');
           Readln(Pos1)
     Until (Pos1>0) And (Pos1<=Size);
     Repeat
           Write('Enter first position: ');
           Readln(Pos2)
     Until (Pos2>0) And (Pos2<=Size);
     Mas[Pos1]:=Mas[Pos2];
End;
{____________________________________________________________________________}
Procedure SravnPoChisl(Mas:Rec;Size:byte);{Сравнение элементов по числовому ключу}
          Var
             Pos1,Pos2:byte;
Begin
     Repeat
           Write('Enter first position: ');
           Readln(Pos1)
     Until (Pos1>0) And (Pos1<=Size);
     Repeat
           Write('Enter first position: ');
           Readln(Pos2)
     Until (Pos2>0) And (Pos2<=Size);
     If Mas[Pos1].Kol>Mas[Pos2].Kol
        Then
            Writeln('Cost of ',Pos1,'Goods> Cost of ',Pos2,' Goods!');
     If Mas[Pos2].Kol>Mas[Pos1].Kol
        Then
            Writeln('Cost of ',Pos2,' Goods > Cost of ',Pos1,' Goods!');
     If Mas[Pos2].Kol=Mas[Pos1].Kol
        Then
            Writeln('Cost of ',Pos1,' Goods = Cost of ',Pos2,' Goods!');
     Write('Press <Enter> to continue: ');
     Readln
End;
{____________________________________________________________________________}
Procedure SravnAlf(Mas:Rec;Size:word);{Сравнение элементов записей массива по алфавитному ключу}
          Var
             Key:byte;
             Pos1,Pos2:byte;
Begin
     Repeat
           Write('Enter first position: ');
           Readln(Pos1)
     Until (Pos1>0) And (Pos1<=Size);
     Repeat
           Write('Enter first position: ');
           Readln(Pos2)
     Until (Pos2>0) And (Pos2<=Size);

           Write('Enter key for comparison Goods): ');
     Begin
            If Mas[Pos1].TovName>Mas[Pos2].TovName
               Then
                   Writeln('Goods of ',Pos1,' > Goods of ',Pos2,'!');
            If Mas[Pos2].TovName>Mas[Pos1].TovName
               Then
                   Writeln('Goods of ',Pos2,'> Goods of ',Pos1,'!');
            If Mas[Pos1].TovName=Mas[Pos2].TovName
               Then
                   Writeln('Goods of ',Pos1,' = Goods of ',Pos2,'!');
            Write('Press <Enter> to continue: ');
            Readln
            End;

     End;

{____________________________________________________________________________}
Procedure Sort(var Mas:Rec; Size:byte);{Сортировка записей массива по заданному ключу}
          Var
             i,j,Key:byte;
             Elem:Rec;
Begin
     Repeat
           Write('Enter key for sorting(1-Goods,2-Number,3-Cots,4-Supplier):');
           Readln(Key)
     Until (Key>0) And (Key<6);
     Case Key of
          1:
            Begin
                 For i:=2 To Size Do
                 For j:=Size DownTo i Do
                     If Mas[j-1].TovName>Mas[j].TovName
                        Then
                            Begin
                                 Elem[1]:=Mas[j-1];
                                 Mas[j-1]:=Mas[j];
                                 Mas[j]:=Elem[1]
                            End
            End;
          2:
            Begin
                 For i:=2 To Size Do
                 For j:=Size DownTo i Do
                     If Mas[j-1].Kol>Mas[j].Kol
                        Then
                            Begin
                                 Elem[1]:=Mas[j-1];
                                 Mas[j-1]:=Mas[j];
                                 Mas[j]:=Elem[1]
                            End
            End;
          3:
            Begin
                 For i:=2 To Size Do
                 For j:=Size DownTo i Do
                     If Mas[j-1].Stoim>Mas[j].Stoim
                        Then
                            Begin
                                 Elem[1]:=Mas[j-1];
                                 Mas[j-1]:=Mas[j];
                                 Mas[j]:=Elem[1]
                            End
            End;
          4:
            Begin
                 For i:=2 To Size Do
                 For j:=Size DownTo i Do
                     If Mas[j-1].PostName>Mas[j].PostName
                        Then
                            Begin
                                 Elem[1]:=Mas[j-1];
                                 Mas[j-1]:=Mas[j];
                                 Mas[j]:=Elem[1]
                            End
            End;
        End;

End;
{____________________________________________________________________________}
Procedure AddElem(Var Mas:Rec;Var Size:byte);{Вставка элемента в массив записей в позицию Pos}
          Var
             i,Pos:byte;
             Flag,Flag1,Flag3:boolean;
             Ch:char;
Begin
     Flag3:=False;
     If Size<Max
        Then
            Begin
                 With Mas[Size+1] do
                      Begin
                                       Write('Enter Goods: ');
                                       Readln(TovName);
                                       Repeat
                                             Write('Enter Number (from 0 to ',MaxWord,' ); ');
                                        Readln(Kol);
                                       Until (Kol>0) And (Kol<=MaxWord);
                                       Repeat
                                             Write('Enter Cots (from 0 to ',MaxWord,' ); ');
                                        Readln(Kol);
                                       Until (Stoim>0) And (Stoim<=MaxWord);

                                       Write('Enter Supplier: ');
                                       Readln(PostName);
                                       Write('Enter Delivery: ');
                                       Readln(SrokPost);
                           Inc(Size,1);
                           Flag3:=True;
                      End
            End;
     If (Size=Max) And (Flag3=False)
        Then
            Begin
                 Writeln('Лимит заполненных анкет исчерпан!');
                 For i:=1 To Size Do
                     With Mas[i] Do
                          If (TovName=' ')And(Kol=0)And(Stoim=0)And(PostName=' ')And(SrokPost=' ')
                             Then
                                 Begin
                                      Writeln('Но на ',i,' месте пустая анкета;');
                                      Flag:=True
                                 End;
            End;
     If Flag=True
        Then
            Begin
                 Write('Хотите ли Вы добавить новую анкету на одно из вышеперечисленных мест?(y\n)');
                 Repeat
                       Ch:=readkey;
                 Until (Ch='y') Or (Ch='n') Or (Ch='Y') Or (Ch='N');
                 Writeln;
                 Flag1:=False;
                 If (Ch='y') Or (Ch='Y')
                    Then
                        Begin
                             Repeat
                                   Write('Введите позицию, на которую Вы хотите вставить новую акету: ');
                                   Readln(Pos);
                                   With Mas[Pos] Do
If (TovName<>' ')And(Kol<>0)And(Stoim<>0)And(PostName<>' ')And(SrokPost<>' ')And(Pos>0)And(Pos<=Max)
                                           Then
                                              Begin
                                                   Writeln('Вы ввели номер, по которому располагется не пустая анкета, ',
                                                   'повторите пожалуйста ввод!');
                                                   Flag1:=False
                                              End
                                           Else
                                               Flag1:=True
                             Until (Flag1=True) And (Pos<=Size) And (Pos>=1);
                             With Mas[Pos] Do
                                  Begin
                                       Write('Enter Goods: ');
                                       Readln(TovName);
                                       Repeat
                                             Write('Enter Number (from 0 to ',MaxWord,' ); ');
                                             Readln(Kol)
                                       Until (Kol>0) And (Kol<=MaxWord);
                                       Repeat
                                             Write('Enter Cots (from 0 to ',MaxWord,' ); ');
                                             Readln(Stoim)
                                       Until (Stoim>0) And (Stoim<=MaxWord);
                                       Write('Enter Supplier: ');
                                       Readln(PostName);
                                       Write('Enter Delivery: ');
                                       Readln(SrokPost);

                                  End
                        End;
            End;

End;
{____________________________________________________________________________}
Procedure DelElem(Var Mas:Rec; Var Size:Byte);{Удаление элемента массива записей с позиции Pos}
          Var
             i,Pos:word;
Begin
     Repeat
           Write('Enter position for delete Questionnaire: ');
           Readln(Pos)
     Until (Pos>0) And (Pos<=Size);
     With Mas[Pos] do
          Begin
               TovName:=' ';
               Kol:=0;
               stoim:=0;
               PostName:=' ';
               SrokPost:=' ';

          End;
End;
{____________________________________________________________________________}
Procedure Add(Var Mas:Rec;Size:byte);{изменение значения поля записи}
          Var
             Elem,Key,Pos:byte;
             s:string;
Begin
     Repeat
           Write('Enter position of questionnaire in BD(from 1 to ',Size,'): ');
           Readln(Pos)
     Until (Pos>0) And (Pos<=Size);
     Repeat
           Write('Enter position for replacement value(1-Goods,2-Number');
           Write('3-Cots,4-Supplier,5-Delivery): ');
           Readln(Key)
     Until (Key>0) And (Key<6);
     Case Key of
          1:
            Begin
                 Write('Enter Goods for rewriting: ');
                 Readln(s);
                 Mas[Pos].TovName:=s
            End;
          2:
            Begin
                 Write('Enter  Number for rewriting: ');
                 Readln(Elem);
                 Mas[Pos].Kol:=Elem
            End;
          3:
            Begin
                 Write('Enter  Cots for rewriting: ');
                 Readln(Elem);
                 Mas[Pos].Stoim:=Elem
            End;
          4:
            Begin
                 Write('Enter Supplier for rewriting: ');
                 Readln(s);
                 Mas[Pos].PostName:=s
            End;
          5:
            Begin
                 Write('Enter Delivery for rewriting: ');
                 Readln(s);
                 Mas[Pos].SrokPost:=s
            End;
End;
End;
{_;___________________________________________________________________________}
Procedure SearchStr(Mas:Rec; Size:byte);{Поиск строковых парметров записи}
          Var
             i,Key,Count:byte;
             str:string;
Begin
     Write('Enter string for search: ');
     Readln(str);
                Write('Enter Key for Search string Goods ');
          Count:=0;
            Begin
                 For i:=1 To Size Do
                     If Mas[i].TovName=str
                        Then
                            Begin
                                 Writeln('This Goods you can see in ',i,' questionnaire.');
                                 inc(Count,1);
                            End;
                 If Count=0
                    Then
                        Writeln('This questionnaires has not this Goods.');
                 Write('Press <Enter> to continue: ');
                 Readln;
           End;

End;
{____________________________________________________________________________}
Procedure SearchWord(Mas:Rec; Size:byte);{Поиск Number}
          Var
             i,Count:byte;
             Elem:word;
Begin
     Repeat
           Write('Enter Number for search(from 0 to ',MaxWord,'): ');
           Readln(Elem)
     Until (Elem>=0) And (Elem<=65535);
     Count:=0;
     For i:=1 To Size Do
         If Mas[i].Kol=Elem
            Then
                Begin
                     Writeln('This Number you can see in ',i,' questionnaire.');
                     inc(Count,1);
                End;
     If Count=0
        Then
            Writeln('This questionnaires has not this Number.');
     Write('Press <Enter> to continue: ');
     Readln
End;
{____________________________________________________________________________}
Procedure Print(Mas:Rec; Size:word);{Печать всей базы анкет}
          Var
             i:byte;
Begin
     Writeln(lst,'OutPut of questionnaires: ');
     For i:=1 To Size Do
         With Mas[i] Do
              Begin
                   Writeln(lst,'':28,'Questionnaire №',i);
                   Writeln(lst,'1) Goods: ',TovName);
                   Writeln(lst,'2) Number: ',Kol);
                   Writeln(lst,'3) Cots: ',Stoim);
                   Writeln(lst,'4) Supplier:',PostName);
                   Writeln(lst,'5) Delivery: ',SrokPost);

                   Write(lst,'********************************************************************************');
              End
End;
{____________________________________________________________________________}
Procedure PrintOne(Mas:Rec; Size:word);{Печать одной анкеты}
          Var
             i,Pos:byte;
Begin
     Repeat
           Write('Enter position of Questionnaire in BD(from 1 to ',Size,'): ');
           Readln(Pos)
     Until (Pos>0) And (Pos<=Size);
     Writeln(lst,'OutPut of questionnaires: ');
     With Mas[Pos] Do
          Begin
               Writeln(lst,'':28,'Questionnaire №',i);
               Writeln(lst,'1) Goods: ',TovName);
               Writeln(lst,'2) Number: ',Kol);
               Writeln(lst,'3) Cots: ',Stoim);
               Writeln(lst,'4) Supplier:',PostName);
               Writeln(lst,'5) Delivery: ',SrokPost);

               Write(lst,'********************************************************************************');
          End
End;
{____________________________________________________________________________}
Procedure Ran(var F:text; var Flag:boolean);
          var
             Str:string;
Begin
    Flag:=True;
    Assign(F,'C:\InPut.txt');
    {$I-}Reset(f);{$I+}
    if IoResult<>0
       Then
           Begin
                Writeln('file not found!!!');
                flag:=False;
                exit;
           End ;
       {Else
           close(f);}
               Write('Input new name for file: ');
    Readln(str);
    Rename(F,'C:\'+str);
End;
Procedure Del(var F:Text;var Flag:boolean);
Begin
     Flag:=True;
     Assign(F,'C:\InPut.txt');
     {$I-}Reset(f);{$I+}
     if IoResult<>0
       Then
           Begin
                Writeln('file not found!!!');
                flag:=False;
                exit;
           End
       Else
           close(f);
       erase(F);
End;

Begin
End.