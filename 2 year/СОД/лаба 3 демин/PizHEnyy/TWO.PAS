{��⥩�� �. ����-�-1;
������ୠ� ࠡ�� �14
                                �᫮���:
��⮢�� ����:�������� ⮢��, ������⢮ �� ᪫���,
             �⮨����� �������, �������� ���⠢騪�, �ப ���⠢��}
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
           Writeln('1 - ���� �� 䠩��;   2 - ���� � ���᮫�;');
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
           Writeln('1) �뢮� � 䠩�;                            2)  �뢮� �� ���᮫�;');
           Writeln('3) �뢮� ������ �� ��࠭;                   4)  �뢮� ������ � ⥪��� 䠩�;');
           Writeln('5) ��᢮���� �������� ����⠭��� ���祭��;6)  ��ᢠ������ ��ꥪ⮢;');
           Write('7) �ࠢ����� �� �᫮���� �����;            8)  Cࠢ����� �� ��䠢�⭮�� �����; ');
           Writeln('9) ����஢�� ����� �� ��������� �����;     10) ��⠢�� ����� ������;');
           Write('11) �������� ������;                        12) ��������� ���祭�� ���� �����;');
           Writeln(' 13) ���� ��ப���� ��ࠬ��஢ �����;       14) ���� ������⢮ ⮢��; ');
           Writeln('15) ����� �ᥩ ���� �����;                 16) ����� ����� ������;');
           Writeln('17) ��२��������� 䠩��;                    18) �������� 䠩��;');
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