uses unit1,crt;
var mas:REC;
    File1,File2:text;
    constant:array[1..5] of boolean;
    Key:char;
    buffer:byte;
    t:byte;
    stop:boolean;
begin
TextBackground(white);
TextColor(black);
repeat
Window(1,1,80,25);
ClrScr;
Writeln('//////////////////////////////STUDENTS DATABASE////////////////////////////////');
DrawLine;
Writeln('1   | Add info about students ');
DrawLine;
Writeln('2   | Watch current info');
DrawLine;
Writeln('3   | Save current info to file');
DrawLine;
Writeln('4   | Search for object in file');
DrawLine;
Writeln('5   | Correct info');
DrawLine;
Writeln('6   | Sort info');
DrawLine;
Writeln('0   | Delete Info');
DrawLine;
Writeln('Esc | Quit');
DrawLine;
buffer:=WhereY;
case readkey of
     '1':begin
         ClrScr;
         Stop:=false;
         Writeln('//////////////////////////////////INFO INPUT///////////////////////////////////');
         DrawLine;
         Writeln('1   | From keyboard');
         DrawLine;
         Writeln('2   | From file ');
         DrawLine;
         Writeln('Esc | Cancel');
         case readkey of
              '1':begin
                  KeyBoardInput(Mas);
                  end;
              '2':begin
                  FileInput(Mas,File1);
                  end;
              #27:begin
                  end;
              end;
         end;
     '2':begin
         ClrScr;
         Writeln('//////////////////////////////////WATCH INFO///////////////////////////////////');
         DrawLine;
         ConsoleOutput(Mas);
         DrawLine;
         Writeln('Esc | Cancel');
         repeat
         until readkey=#27;
         end;
     '3':begin
         Writeln('//////////////////////////////////SAVE FILE////////////////////////////////////');
         DrawLine;
         FileOutput(Mas,File2);
         end;
     '4':begin
         ClrScr;
         Writeln('/////////////////////////////////SEARCH OBJECT/////////////////////////////////');
         DrawLine;
         SearchObject(Mas);
         end;
     '5':begin
         ClrScr;
         Writeln('//////////////////////////////////CORRECT INFO////////////////////////////////');
         DrawLine;
         Writeln('1   | Assign constant value to object');
         DrawLine;
         Writeln('2   | Assign value of object B to onject A ');
         DrawLine;
         Writeln('Esc | Cancel');
         case readkey of
              '1':begin
                  AssignConst(Mas,constant);
                  end;
              '2':begin
                  AssignObject(Mas,constant);
                  end;
              #27:begin
                  end;
              end;
         end;
     '6':begin
         ClrScr;
         Writeln('////////////////////////////////////SORT INFO//////////////////////////////////');
         DrawLine;
         if (mas[1].FirstName='') or (mas[1].Secondname='') or (mas[1].ThirdName='') then
            begin
            writeln('There is no info yet...');
            DrawLine;
            Writeln('Esc | Cancel');
            repeat
            until readkey=#27;
            end
         else
         begin
         Writeln('1   | Sort by name');
         DrawLine;
         Writeln('2   | Sort by salary ');
         DrawLine;
         Writeln('Esc | Cancel');
           case readkey of
              '1':begin
                  SortString(Mas);
                  end;
              '2':begin
                  SortVal(Mas);
                  end;
              #27:begin
                  end;
              end;
           end;
         end;
     '0':begin
         DeleteRec(Mas);
         end;
     #27:halt;
     end;
until 0>1;
end.