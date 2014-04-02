Unit unit1;
{////////////////////////////////////////////////////////////////////////////}
Interface
{////////////////////////////////////////////////////////////////////////////}
Uses crt;
{////////////////////////////////////////////////////////////////////////////}
Const Max=10;MaxLen=80;
Type RecType= record
               FirstName,SecondName,ThirdName:string[10];
               JobType:string[10];
               Salary:integer;
               end;
     REC=array[1..Max] of RecType;

{////////////////////////////////////////////////////////////////////////////}
Var A:REC;
    FileA:text;
    Size,Size2:byte;
Procedure KeyBoardInput(var A:REC);
Procedure ConsoleOutput(var A:REC);
Procedure FileInput(var A:REC;var FileA:text);
Procedure FileOutput(var A:REC;var FileB:text);
Procedure SearchObject(A:REC);
Procedure AssignConst(var A:REC;var constant:array of boolean);
Procedure AssignObject(var A:REC;var constant:array  of boolean);
Procedure SortString(var A:REC);
Procedure SortVal(var A:REC);
Procedure DrawLine;
Procedure DeleteRec(var A:REC);
{////////////////////////////////////////////////////////////////////////////}
Implementation
{////////////////////////////////////////////////////////////////////////////}
{////////////////Ввод с клавиатуры///////////////////////////////////////////}
Procedure KeyBoardInput(var A:REC);
Var i,k:byte;
Begin
     Repeat
     ClrScr;
     Writeln('//////////////////////////////////INFO INPUT///////////////////////////////////');
     DrawLine;
     size2:=size;
     Write('Input amount of students (from 1 to ',Max,' ): ');
     {$I-}Readln(Size){$I+}
     Until (Size>=0) And (Size<=Max) And (IoResult=0);
Writeln('Input of info: ');
For i:=1 To Size Do
     Begin
     ClrScr;
     Writeln('//////////////////////////////////INFO INPUT///////////////////////////////////');
     DrawLine;
     Writeln('':28,'Student #',i);
     Write('Input First name: ');
     Readln(a[size2+i].FirstName);
     Write('Input Second name: ');
     Readln(a[size2+i].SecondName);
     Write('Input Third name: ');
     Readln(a[size2+i].ThirdName);
     Write('Input Job type: ');
     Readln(a[size2+i].JobType);
     Window(1,WhereY,78,21);
     Repeat
     ClrScr;
     Write('Input Salary: ');
     {$I-}Readln(a[size2+i].Salary);{$I+}
     Until IOResult=0;
     Writeln;
     WriteLn('Done!!!');
     ReadLn;
     Window(1,1,80,21);
     End;
End;
{////////////////Вывод на консоль///////////////////////////////////////////}
Procedure ConsoleOutput(var A:REC);
Var i:byte;
Begin
If (a[1].FirstName='') or (a[1].SecondName='') or (a[1].ThirdName='') then
writeln('There is no info yet...')
else 
     begin
     WriteLn('Students:');
     For i:=1 to (Size+Size2) do
        Begin
        Write(i,')',a[i].FirstName:10,' | ');
        Write(A[i].SecondName:10,' | ');
        Write(A[i].ThirdName:10,' | ');
        Write(A[i].JobType:10,' | ');
        WriteLn(A[i].Salary:7);
        End;
     end;
End;
{////////////////Ввод из файла///////////////////////////////////////////}
Procedure FileInput(var A:REC;var FileA:text);
var s:string;
    Path:string;
    k,i,j,v:byte;
    code:integer;
    empty:boolean;
Begin
while 1>0 do
      begin
      ClrScr;
      Writeln('//////////////////////////////////INFO INPUT///////////////////////////////////');
      DrawLine;
      WriteLn('Input file path (C:\FILE.TXT):');
      ReadLn(Path);
      If Path='' then Empty:=true;
      Assign(FileA,Path);
      {$I-}Reset(FileA);{$I+}
      If (IOResult=0) and not(Empty) then break;
      empty:=false;
      end;
While Not Eof(FileA) Do
      Begin
      Inc(i);
      For j:=1 to 6 Do
           Begin
              Case j Of
                   1:ReadLn(FileA,s);
                   2:Begin
                     ReadLn(FileA,s);
                     A[i].FirstName:=Copy(s,3,length(s)-2);
                     End;
                   3:Begin
                     ReadLn(FileA,s);
                     A[i].SecondName:=Copy(s,3,length(s)-2);
                     End;
                   4:Begin
                     ReadLn(FileA,s);
                     A[i].ThirdName:=Copy(s,3,length(s)-2);
                     End;
                   5:Begin
                     ReadLn(FileA,s);
                     A[i].JobType:=Copy(s,3,length(s)-2);
                     End;
                   6:Begin
                     ReadLn(FileA,s);
                     s:=Copy(s,3,length(s)-2);
                     Val(s,v,code);
                     If code=0 Then
                     A[i].Salary:=v;
                     End;
              End;
           end;
      end;
Writeln;
WriteLn('Done!!!');
ReadLn;
Close(FileA);
Size:=i;
i:=0;
End;
{//////////////Вывод в файл/////////////////////////////////////////////}
Procedure FileOutput(var A:REC;var FileB:text);
Var Path:string;
    Empty:boolean;
    i:byte;
Begin
While 1>0 Do
      Begin
      empty:=false;
      ClrScr;
      Writeln('//////////////////////////////////SAVE FILE////////////////////////////////////');
      DrawLine;
      WriteLn('Input file path to save (C:\FILE.TXT):');
      ReadLn(Path);
      If Path='' Then Empty:=true;
      Assign(FileB,Path);
      {$I-}Rewrite(FileB);{$I+}
      If (IOResult=0) And Not(Empty) Then break;
      End;
For i:=1 to Size Do
    begin
    WriteLn(FileB,'STUDENT #',i,':');
    WriteLn(FileB,'1)',a[i].FirstName);
    WriteLn(FileB,'2)',a[i].SecondName);
    WriteLn(FileB,'3)',a[i].ThirdName);
    WriteLn(FileB,'4)',a[i].JobType);
    WriteLn(FileB,'5)',a[i].Salary);
    end;
Close(FileB);
i:=0;
Writeln;
WriteLn('Done!!!');
ReadLn;
End;
{/////////////Поиск объекта////////////////////////////////////////////}
Procedure SearchObject(A:REC);
var s:string;
    v,i:integer;
    found:boolean;
Begin;
found:=false;
Writeln('Input information about element you search:');
Writeln('Input first name:');
ReadLn(s);
    For i:=1 to size do
    If s=a[i].FirstName then
       begin
       Writeln('Input second name:');
       ReadLn(s);
       If s=a[i].SecondName then
          begin
          Writeln('Input third name:');
          ReadLn(s);
          If s=a[i].ThirdName then
             begin
             Writeln('Input job type:');
             ReadLn(s);
             If s=a[i].JobType then
                begin
                Writeln('Input salary:');
                ReadLn(v);
                If v=a[i].Salary then
                   begin
                   Writeln('Student found in position ',i);
                   found:=true;
                   end;
                end;
             end;
          end;
       break;
       end;
WriteLn;
Writeln('Done!!!');
readln;
If found=false then 
  begin
  writeln('No student found!!!');
  DrawLine;
  Writeln('Esc | Cancel');
  repeat
  until readkey=#27;
  end;
End;
{/////////////Присвоение константы///////////////////////////////////////}
Procedure AssignConst(var A:REC;var constant:array of boolean);
var m,i,c:byte;
begin
ClrScr;
Writeln('//////////////////////////////////CORRECT INFO/////////////////////////////////');
DrawLine;
If (a[1].FirstName='') or (a[1].SecondName='') or (a[1].ThirdName='') then
begin
writeln('There is no info yet...');
end
else
If not(Constant[m]) then
   begin
   repeat
   ClrScr;
   Writeln('//////////////////////////////////CORRECT INFO/////////////////////////////////');
   DrawLine;
   Writeln('Input number of object you are assigning constant:');
   {$I-}Readln(m);{$I+}
   until (m>=1) and (m<=5) and (IOresult=0);
   Writeln('Input constant values for current record:');
   ClrScr;
   Writeln('//////////////////////////////////CORRECT INFO/////////////////////////////////');
   DrawLine;
   Writeln('Input number of field you want to change:');
   Writeln('1 | First name');
   Writeln('2 | Second name');
   Writeln('3 | Third name');
   Writeln('4 | Job Type');
   Writeln('5 | Salary');
   repeat
   readln(c);
   until (c>0) and (c<6);
   case c of
   1:begin
     Write('Input First name: ');
     Readln(a[m].FirstName);
     end;
   2:begin
     Write('Input Second name: ');
     Readln(a[m].SecondName);
     end;
   3:begin
     write('Input Third name: ');
     Readln(a[m].ThirdName);
     end;
   4:begin
     Write('Input Job type: ');
     Readln(a[m].JobType);
     end;
   5:begin
     Window(1,WhereY,78,21);
     Repeat
     ClrScr;
     Write('Enter Salary: ');
     {$I-}Readln(a[m].Salary);{$I+}
     Until IOResult=0;
     Writeln;
     WriteLn('Done!!!');
     ReadLn;
     window(1,1,80,21);
     constant[m]:=true;
     end;
   end;
   end
else
     begin
     Writeln;
     Writeln('Your object consist of constant fields!!!');
     DrawLine;
     Writeln('Esc | Cancel');
     repeat
     until readkey=#27;
     end;
End;
{/////////// Присвоение объекта/////////////////////////////////////////}
Procedure AssignObject(var A:REC;var constant:array  of boolean);
var m,n,i:byte;
Begin
ClrScr;
Writeln('//////////////////////////////////CORRECT INFO/////////////////////////////////');
DrawLine;
If (a[1].FirstName='') or (a[1].SecondName='') or (a[1].ThirdName='') then
   begin
   writeln('There is no info yet...');
   end
else
    begin
    repeat
    Writeln('Input number of object A ("left operand"):');
    {$I-}Readln(m);{$I+}
    until (m>=1) and (m<=5) and (IOresult=0);
    If not(Constant[m]) then
       begin
       Writeln('Input number of object B ("right operand"):');
       Readln(n);
       a[m].FirstName:=a[n].FirstName;
       a[m].SecondName:=a[n].SecondName;
       a[m].ThirdName:=a[n].ThirdName;
       a[m].JobType:=a[n].JobType;
       a[m].Salary:=a[n].Salary;
       WriteLn('Done!!!');
       Readln;
       end
    else
       begin
       Writeln;
       Writeln('Your object consist of constant fields!!!');
       DrawLine;
       Writeln('Esc | Cancel');
       repeat
       until readkey=#27;
       end;
    end;
End;
{/////////////////////////////////////Изображение линии//////////////////////////////}
Procedure DrawLine;
var i:byte;
begin
for i:=1 to 80 do write ('_');
end;
{/////////////////////////////////////Сортировка по алфавиту//////////////////////////////}
Procedure SortString(var A:REC);
    procedure SwapREC(var x,y:RecType);
    var s:recType;
    begin
    s:=x;
    x:=y;
    y:=s;
    end;
var i,j:byte;
begin
ClrScr;
writeln('///////////////////////////////////////SORT INFO///////////////////////////////');
DrawLine;
for j:=1 to size-1 do
    for i:=1 to size-j do
        if concat(a[i].FirstName,a[i].SecondName,a[i].ThirdName) >
        concat(a[i+1].FirstName,a[i+1].SecondName,a[i+1].ThirdName)
        then
           SwapREC(a[i],a[i+1]);
writeln;
writeln('Done!!!');
Readln;
end;
{/////////////////////////////////////Сортировка по числу//////////////////////////////}
Procedure SortVal(var A:REC);
    Procedure SwapRec(var x,y:RecType);
    var s:REcType;
    begin
    s:=x;
    x:=y;
    y:=s;
    end;
var i,j:byte;
begin
ClrScr;
Writeln('///////////////////////////////////////SORT INFO///////////////////////////////');
DrawLine;
for j:=1 to (size-1) do
    for i:=1 to (size-j) do
        If a[i].Salary >a[i+1].Salary then
        SwapRec(a[i],a[i+1]);
writeln;
writeln('Done!!!');
Readln;
end;
{/////////////////////////////////////Очистка полей записи//////////////////////////////}
Procedure DeleteRec(var A:REC);
var i:byte;
    key:char;
begin
ClrScr;
writeln('///////////////////////////////////DELETE INFO//////////////////////////////////');
DrawLine;
writeln('Are you sure you want to delete current info?');
writeln('[Y/N]');
repeat
Key:=readkey;
until (key='n') or (key='y') or (key=#27);
If (key='y') then
for i:=1 to size do
    begin
    A[i].FirstName:='';
    A[i].SecondName:='';
    A[i].ThirdName:='';
    A[i].JobType:='';
    A[i].Salary:=0;
    end;
writeln;
Writeln('Done!!!');
readln;
end;
Begin
End.