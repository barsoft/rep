Program sod2;
uses crt;
const maxlen=80;
var s1:string;
    i,lengths,k:byte;
    p:char;
    osc:boolean;
  Procedure DrawLine;
begin
for i:=1 to maxlen do
write('_');
end;
  Procedure Right(var s:string;l:byte);
var t1,t2:string;
begin
osc:=true;
t1:='';
t2:='';
For i:=1 to (maxlen-l) do t1:=t1+' ';
For i:=1 to l do
    if (s[i]=' ') and (s[i+1]=' ') then t1:=t1+' ' else
         begin
         osc :=false;
         t2:=t2+s[i];
         end;
s:=t1+t2;
end;
  Begin
TextColor(Black);
TextBackground(White);
repeat
clrscr;
s1:='';
WriteLn('Input STRING:');
WriteLn;
ReadLn(s1);
lengths:=Ord(s1[0]);
DrawLine;
If lengths>maxlen then WriteLn('Large stiring!!!') else
   begin
   Right(s1,lengths);
   If lengths=0 then WriteLn('Empty STRING!!!') else
   If osc then WriteLn('Only SPACE characters!!!')
   else
       begin
       WriteLn('New string:');
       Write(s1);
       end;
   end;
WriteLn('Try Again?');
WriteLn('[Y/N]');
    repeat
    p:=readkey;
    until (p='n') or (p='y') or (p=#27) ;
until (p='n') or (p=#27);
end.