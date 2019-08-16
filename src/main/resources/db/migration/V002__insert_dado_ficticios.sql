insert into usuario(id, nome, senha, email) values (1, 'Jessica', '$2a$10$VD1RYwBy7Ybo2jyjcoH.y.INY0Fea2X2lSu.0D8DOW6IL06wm.j.i', 'jessica@gmail.com');
insert into usuario(id, nome, senha, email) values (2, 'Túlio', '$2a$10$VD1RYwBy7Ybo2jyjcoH.y.INY0Fea2X2lSu.0D8DOW6IL06wm.j.i', 'tulio.engcomp@gmail.com');

insert into perfil(id, nome) values (1, 'Administrador');
insert into perfil(id, nome) values (2, 'Auxiliar Administrativo');

insert into permissao(id, nome, descricao) values (1, 'CAS_TOP', 'Cadastrar topicos');
insert into permissao(id, nome, descricao) values (2, 'UPD_TOP', 'Atualizar topicos');
insert into permissao(id, nome, descricao) values (3, 'DEL_TOP', 'Deletar topicos');
insert into permissao(id, nome, descricao) values (4, 'CHK_ESTOQUE', 'Verificar estoque');

insert into perfil_permissao(fk_perfil_id, fk_permissao_id) values (1, 1);
insert into perfil_permissao(fk_perfil_id, fk_permissao_id) values (1, 2);
insert into perfil_permissao(fk_perfil_id, fk_permissao_id) values (1, 3);
insert into perfil_permissao(fk_perfil_id, fk_permissao_id) values (2, 4);

insert into usuario_perfil(fk_usuario_id, fk_perfil_id) values (1, 1);
insert into usuario_perfil(fk_usuario_id, fk_perfil_id) values (1, 2);
insert into usuario_perfil(fk_usuario_id, fk_perfil_id) values (2, 2);



insert into curso(id, nome, categoria) values (1, 'HTML 5', 'FRONT-END');
insert into topico(id, data_criacao, mensagem, status, titulo, fk_usuario_id, fk_curso_id)
values (1, now(), 'Jquery é uma biblioteca java scripty que é usada somente para navegador desktop','FECHADO', 'Duvida jquery', 1, 1);

insert into topico(id, data_criacao, mensagem, status, titulo, fk_usuario_id, fk_curso_id)
values (2, now(), 'Saas vale a pena ser tulizado ou existe outros melhores, qual sua vantagem','NAO_RESPONDIDO', 'Duvida sass', 1, 1);

insert into resposta(id, data_criacao, mensagem, solucao, fk_usuario_id, fk_topico_id ) values (1, now(), 'Sim pode ser usado em diversos navegadores, em diversas plataformas diferentes, ' ||
 'foi feito com esse conceito de poder ser utilizado por diversos navegadores', true,2,1 )