program laba_1;
uses crt;
const max=100; maxline=80; maxval=98; maxnum=10;
var a:array[1..max] of byte;
    v,x,y,i,j,k,n,m,maxk:byte;
    empty,out:boolean;
    p:char;
  Procedure DrawLine;
begin
window(1,n+4,80,25);
for i:=1 to maxline do write('_');
end;
  Procedure NewIndex;
begin
for i:=1 to x do
    for j:=1 to m do
        if (j mod 2<>0) then
        if (i=x) and (j>y) then break
        else
        inc(k);
end;
  Procedure PutTab;
begin
for i:=1 to n do
    begin
    for j:=1 to m do
        begin
        if (j mod 2=0)  then
           Write('0':3,'|')
        else
           begin
           inc(k);
           a[k]:=random(maxval)+1;
           Write(a[k]:3,'|');
           end;
        end;
    WriteLn;
    end;
end;
  Procedure InitArray;
begin
repeat
clrscr;
WriteLn('Enter size of array:');
WriteLn;
{$I-}
repeat
window(1,3,80,25);
clrscr;
WriteLn('Enter x <=10:');
ReadLn(n);
until IOresult=0;
WriteLn;
repeat
window(1,5,80,25);
clrscr;
WriteLn('Enter y <=10:');
ReadLn(m);
until IOresult=0;
{$I+}
until (n>1) and (n<=maxnum) and (m>1) and (m<=maxnum);
window(1,1,80,25);
clrscr;
PutTab;
WriteLn;
maxk:=k;
for k:=1 to maxk do
Write(a[k],' ');
end;
  Procedure GetTab;
begin
repeat
DrawLine;
window(1,n+5,80,25);
TextBackground(White);
TextColor(Black);
clrscr;
k:=0;
{$I-}
WriteLn('Enter x and y you search:');
repeat
window(1,n+5,80,25);
clrscr;
Write('x=');
ReadLn(x);
until IOresult=0;
repeat
window(1,n+7,80,25);
clrscr;
Write('y=');
ReadLn(y);
until IOresult=0;
{$I+}
if y mod 2=0 then empty:=true;
if (x>n) or (y>n) or (x<1) or (y<1) then
   out:=true;
NewIndex;
WriteLn;
if not out then
   if not(empty) then
      WriteLn('Element=',a[k],' found in position ',k)
   else WriteLn('Error!!! Empty place!!!')
else WriteLn('Error!!! Element is out of matrix!!!');
WriteLn;
WriteLn('Try Again?');
WriteLn('[Y/N]');
repeat
p:=readkey;
until (p='n') or (p='y') or (p=#27) ;
until p<>'y';
end;
{///////////MAIN PROGRAM/////////////}
begin
Randomize;
TextBackground(White);
TextColor(Black);
InitArray;
GetTab;
end.
