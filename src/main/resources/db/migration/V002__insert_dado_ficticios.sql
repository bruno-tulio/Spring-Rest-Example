insert into usuario(id, nome, senha, usuario) values (1, 'Jessica', '1234', 'jessica');
insert into usuario(id, nome, senha, usuario) values (2, 'Túlio', '1234', 'tulio.engcomp');
insert into curso(id, nome, categoria) values (1, 'HTML 5', 'FRONT-END');
insert into topico(id, data_criacao, mensagem, status, titulo, fk_usuario_id, fk_curso_id)
values (1, now(), 'Jquery é uma biblioteca java scripty que é usada somente para navegador desktop','FECHADO', 'Duvida jquery', 1, 1);

insert into topico(id, data_criacao, mensagem, status, titulo, fk_usuario_id, fk_curso_id)
values (2, now(), 'Saas vale a pena ser tulizado ou existe outros melhores, qual sua vantagem','NAO_RESPONDIDO', 'Duvida sass', 1, 1);

insert into resposta(id, data_criacao, mensagem, solucao, fk_usuario_id, fk_topico_id ) values (1, now(), 'Sim pode ser usado em diversos navegadores, em diversas plataformas diferentes, ' ||
 'foi feito com esse conceito de poder ser utilizado por diversos navegadores', true,2,1 )