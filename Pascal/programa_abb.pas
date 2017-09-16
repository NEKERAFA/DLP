program programa_abb;

uses abb;

var
   arbol : tABB;


procedure preordenParentizado(A :tABB);
begin
   write('(');
   if not esArbolVacio(A) then
      if not esArbolvacio(hijoIzquierdo(A)) or not esArbolvacio(hijoDerecho(A)) then begin
        write(' ',Raiz(A), ' ');
        preordenParentizado(hijoIzquierdo(A));
        write(' ');
        preordenParentizado(hijoDerecho(A))
     end
   	 else
     		write(' ',Raiz(A), ' ');
   write(')');
end;


begin
   arbolVacio(arbol);
   insertarClave(arbol, 4);
   insertarClave(arbol, 4);
   insertarClave(arbol, 2);
   insertarClave(arbol, 6);
   insertarClave(arbol, 1);
   insertarClave(arbol, 3);
   insertarClave(arbol, 5);
   insertarClave(arbol, 7);

   preordenParentizado(arbol); writeln;

   writeln('buscar 1...', Raiz(buscarClave(arbol,1)));
   writeln('buscar 2...', Raiz(buscarClave(arbol,2)));
   writeln('buscar 3...', Raiz(buscarClave(arbol,3)));
   writeln('buscar 4...', Raiz(buscarClave(arbol,4)));
   writeln('buscar 5...', Raiz(buscarClave(arbol,5)));
   writeln('buscar 6...', Raiz(buscarClave(arbol,6)));
   writeln('buscar 7...', Raiz(buscarClave(arbol,7)));

   write('eliminar 5...');
   eliminarClave(arbol, 5);
   preordenParentizado(arbol); writeln;

   write('eliminar 6...');
   eliminarClave(arbol,6);
   preordenParentizado(arbol); writeln;

   write('eliminar 4...');
   eliminarClave(arbol,4);
   preordenParentizado(arbol); writeln;

   write('eliminar 2...');
   eliminarClave(arbol,2);
   preordenParentizado(arbol); writeln;

end.
