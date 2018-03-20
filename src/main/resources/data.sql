insert into User (id, password, password_confirm, username) values
	(1, 'teste1', 'teste1', 'ti123456'),
	(2, 'user01', 'user01', 'user01'),
	(3, 'user02', 'user02', 'user02'),
	(4, 'user03', 'user03', 'user03');

insert into Autor (id, nome) values
	(1, 'João Vitor'),
	(2, 'Alice Pedroso'),
	(3, 'Lucas Andrade');
	
insert into Livro (id, capa, isbn, nome, quantidade_paginas, autor_id) values
	(1,'','','Alice no Pais das Maravilhas', 500, 1),
	(2,'','','As Crônicas de Gelo e Fogo', 1000, 2),
	(3,'','','Piada Mortal', 800, 1),
	(4,'','','Crimes Federais', 2000, 3),
	(5,'','','Armas da Persuação', 1500, 3),
	(6,'','','Copa do Mundo', 300, 3);