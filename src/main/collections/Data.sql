-- =================================================================
-- CONFIGURACIÓN INICIAL Y LIMPIEZA
-- =================================================================
USE album;
SET FOREIGN_KEY_CHECKS = 0; 
START TRANSACTION;

-- Limpieza de Tablas (Opcional, para reiniciar los IDs)
ALTER TABLE `user` AUTO_INCREMENT = 1;
ALTER TABLE `album` AUTO_INCREMENT = 1;
ALTER TABLE `sticker` AUTO_INCREMENT = 1;
ALTER TABLE `pack` AUTO_INCREMENT = 1;
ALTER TABLE `reward` AUTO_INCREMENT = 1;

-- =================================================================
-- 1. INSERTAR USUARIOS (Mínimo 10 Registros)
-- =================================================================
-- 1 Admin y 10 Users.

INSERT INTO `user` (username, email, password, nombre, apellido, `role`, telefono, avatar_url, created_at) 
VALUES 
('admin_creator', 'admin@fantasy.com', 'hash_admin', 'Elara', 'Guardiana', 'ADMIN', '100010000', NULL, NOW()), -- ID 1 (@ADMIN_ID)
('colec_a', 'a@test.com', 'hash_a', 'Alex', 'Aura', 'USER', '200020000', '/avatars/a.jpg', NOW()), -- ID 2 (@USER_A)
('colec_b', 'b@test.com', 'hash_b', 'Bri', 'Blaze', 'USER', '300030000', NULL, NOW()), -- ID 3 (@USER_B)
('colec_c', 'c@test.com', 'hash_c', 'Cain', 'Cryo', 'USER', '400040000', NULL, NOW()),
('colec_d', 'd@test.com', 'hash_d', 'Dee', 'Dusk', 'USER', '500050000', '/avatars/d.jpg', NOW()),
('colec_e', 'e@test.com', 'hash_e', 'Elora', 'Earth', 'USER', '600060000', NULL, NOW()),
('colec_f', 'f@test.com', 'hash_f', 'Finn', 'Flame', 'USER', '700070000', NULL, NOW()),
('colec_g', 'g@test.com', 'hash_g', 'Gwen', 'Gale', 'USER', '800080000', NULL, NOW()),
('colec_h', 'h@test.com', 'hash_h', 'Hulk', 'Iron', 'USER', '900090000', NULL, NOW()),
('colec_i', 'i@test.com', 'hash_i', 'Iris', 'Ice', 'USER', '123456789', NULL, NOW()),
('colec_j', 'j@test.com', 'hash_j', 'Jax', 'Jungle', 'USER', '987654321', NULL, NOW()); -- ID 11

SET @ADMIN_ID = 1;
SET @USER_A = 2; -- Alice (Completó 1 álbum, compró packs)
SET @USER_B = 3; -- Bob (Completó 1 álbum, compró muchos packs)
SET @USER_J = 11; -- Jax (Solo usuario, no ha comprado ni completado)


-- =================================================================
-- 2. INSERTAR ÁLBUMES (Mínimo 10 Stickers totales, 3 Álbumes)
-- =================================================================
INSERT INTO `album` (titulo, descripcion, categoria, dificultad, creador_admin_id, total_figuritas, publicado, created_at)
VALUES 
('Bestias Elementales Vol. I', 'Criaturas de agua y tierra de la región inicial.', 'Criaturas', 'FACIL', @ADMIN_ID, 30, TRUE, NOW()), -- ID 1 (@ALBUM1_ID)
('Guardianes Legendarios del Nexo', 'Monstruos ancestrales y épicos.', 'Leyendas', 'DIFICIL', @ADMIN_ID, 50, TRUE, NOW()), -- ID 2 (@ALBUM2_ID)
('Liga de Entrenadores Élite', 'Figuritas de los 10 mejores campeones.', 'Personajes', 'MEDIO', @ADMIN_ID, 40, TRUE, NOW()); -- ID 3

SET @ALBUM1_ID = 1;
SET @ALBUM2_ID = 2;


-- =================================================================
-- 3. INSERTAR STICKERS (Mínimo 10 Registros)
-- =================================================================
-- Álbum 1 (ID 1): 5 Stickers
INSERT INTO `sticker` (album_id, nombre, numero, rareza, stock_total, stock_disponible, imagen_url, created_at)
VALUES 
(@ALBUM1_ID, 'Slime Verde', 1, 'COMUN', 2000, 1900, '/img/s1_slime.png', NOW()), -- ID 1 (@S1_COMUN)
(@ALBUM1_ID, 'Hada Curativa', 2, 'RARA', 300, 280, '/img/s1_hada.png', NOW()),   -- ID 2 (@S1_RARA)
(@ALBUM1_ID, 'Dragoncito Ágil', 3, 'EPICA', 50, 5, '/img/s1_dragon.png', NOW()),     -- ID 3 (@S1_EPICA)
(@ALBUM1_ID, 'Golem de Piedra', 4, 'COMUN', 1500, 1400, '/img/s1_golem.png', NOW()),
(@ALBUM1_ID, 'Planta Venenosa', 5, 'COMUN', 1500, 1300, '/img/s1_planta.png', NOW()); 

SET @S1_COMUN = 1;
SET @S1_RARA = 2;
SET @S1_EPICA = 3;

-- Álbum 2 (ID 2): 5 Stickers
INSERT INTO `sticker` (album_id, nombre, numero, rareza, stock_total, stock_disponible, imagen_url, created_at)
VALUES 
(@ALBUM2_ID, 'Guardián del Bosque', 1, 'COMUN', 1000, 800, '/img/s2_guardian.png', NOW()), -- ID 6
(@ALBUM2_ID, 'Grifo Mensajero', 2, 'RARA', 200, 150, '/img/s2_grifo.png', NOW()),
(@ALBUM2_ID, 'Titán de la Furia', 3, 'EPICA', 70, 10, '/img/s2_titan.png', NOW()), -- ID 8 (@S2_EPICA)
(@ALBUM2_ID, 'Portal Dimensional', 4, 'COMUN', 1200, 1200, '/img/s2_portal.png', NOW()),
(@ALBUM2_ID, 'Elemental de Fuego', 5, 'RARA', 200, 190, '/img/s2_elemental.png', NOW()); -- ID 10

SET @S2_EPICA = 8;
SET @S2_RARA_ALB2 = 10;


-- =================================================================
-- 4. INSERTAR REWARD (Mínimo 3 Registros, uno por Álbum)
-- =================================================================
INSERT INTO `reward` (album_id, tipo, payload_json, created_at) 
VALUES (1, 'BADGE_FLORA', '{"nombre":"Maestro Elemental"}', NOW()); -- ID 1 (@REWARD1_ID)
INSERT INTO `reward` (album_id, tipo, payload_json, created_at) 
VALUES (2, 'AVATAR_TITAN', '{"url":"/assets/titan_avatar.png"}', NOW()); -- ID 2 (@REWARD2_ID)
INSERT INTO `reward` (album_id, tipo, payload_json, created_at) 
VALUES (3, 'MONEDAS_BONUS', '{"cantidad":1000}', NOW()); -- ID 3

SET @REWARD1_ID = 1; 
SET @REWARD2_ID = 2;


-- =================================================================
-- 5. INSERTAR USER_REWARD (Mínimo 10 Registros)
-- Objetivo: Probar TOP_ALBUMS.
-- Álbum 1 (Fácil) completado 7 veces. Álbum 2 (Difícil) completado 3 veces.
-- =================================================================
-- Álbum 1 (ID 1): 7 Completados
INSERT INTO `user_reward` (user_id, album_id, reward_id, estado, claimed_at)
VALUES 
(2, 1, @REWARD1_ID, 'RECLAMADO', NOW()), -- User A
(3, 1, @REWARD1_ID, 'RECLAMADO', NOW()), -- User B
(4, 1, @REWARD1_ID, 'RECLAMADO', NOW()), -- User C
(5, 1, @REWARD1_ID, 'RECLAMADO', NOW()), -- User D
(6, 1, @REWARD1_ID, 'RECLAMADO', NOW()), -- User E
(7, 1, @REWARD1_ID, 'RECLAMADO', NOW()), -- User F
(8, 1, @REWARD1_ID, 'RECLAMADO', NOW()); -- User G

-- Álbum 2 (ID 2): 3 Completados
INSERT INTO `user_reward` (user_id, album_id, reward_id, estado, claimed_at)
VALUES 
(9, 2, @REWARD2_ID, 'RECLAMADO', NOW()), -- User H
(10, 2, @REWARD2_ID, 'RECLAMADO', NOW()), -- User I
(11, 2, @REWARD2_ID, 'RECLAMADO', NOW()); -- User J

-- Total User_Reward: 10 Registros


-- =================================================================
-- 6. INSERTAR PACKS Y PACK_STICKER (Mínimo 10 Packs y 50 Pack_Sticker)
-- Objetivo: Probar PACK_OPEN_RATE.
-- =================================================================
-- 6a. Packs (15 Registros)
-- USER B (ID 3) es el mayor comprador (10 packs)
-- USER A (ID 2) es un comprador menor (5 packs)
INSERT INTO `pack` (user_id, album_id, created_at) 
VALUES 
(2, 1, NOW()), (2, 1, NOW()), (2, 1, NOW()), (2, 2, NOW()), (2, 2, NOW()), -- User A: 5 Packs (IDs 1-5)
(3, 1, NOW()), (3, 1, NOW()), (3, 1, NOW()), (3, 1, NOW()), (3, 1, NOW()), -- User B: 10 Packs (IDs 6-15)
(3, 2, NOW()), (3, 2, NOW()), (3, 2, NOW()), (3, 2, NOW()), (3, 2, NOW());
-- Total Packs: 15. Total Compradores Únicos: 2 (User A y B). Tasa: 15/2 = 7.5

-- 6b. Pack_Sticker (Mínimo 50 Registros - 10 packs * 5 stickers/pack)
-- Insertamos contenido para los primeros 10 packs (50 registros)
INSERT INTO `pack_sticker` (pack_id, sticker_id)
VALUES 
(1, @S1_COMUN), (1, @S1_COMUN), (1, @S1_RARA), (1, @S1_EPICA), (1, @S1_COMUN), -- Pack 1 (Contiene épica)
(2, @S1_COMUN), (2, @S1_COMUN), (2, @S1_COMUN), (2, @S1_COMUN), (2, @S1_RARA),
(3, @S1_COMUN), (3, @S1_COMUN), (3, @S1_COMUN), (3, @S1_COMUN), (3, @S1_RARA),
(4, @S2_COMUN), (4, @S2_COMUN), (4, @S2_RARA_ALB2), (4, @S2_COMUN), (4, @S2_EPICA), -- Pack 4 (Contiene épica)
(5, @S2_COMUN), (5, @S2_COMUN), (5, @S2_COMUN), (5, @S2_COMUN), (5, @S2_RARA_ALB2),
(6, @S1_COMUN), (6, @S1_COMUN), (6, @S1_RARA), (6, @S1_COMUN), (6, @S1_COMUN),
(7, @S1_COMUN), (7, @S1_RARA), (7, @S1_RARA), (7, @S1_COMUN), (7, @S1_COMUN),
(8, @S1_COMUN), (8, @S1_COMUN), (8, @S1_COMUN), (8, @S1_COMUN), (8, @S1_COMUN),
(9, @S1_COMUN), (9, @S1_COMUN), (9, @S1_COMUN), (9, @S1_COMUN), (9, @S1_RARA),
(10, @S1_COMUN), (10, @S1_COMUN), (10, @S1_COMUN), (10, @S1_COMUN), (10, @S1_RARA); -- Pack 10

-- =================================================================
-- 7. INSERTAR USER_STICKER (Mínimo 10 Registros en la colección)
-- =================================================================
-- Simula la colección de USER A (ID 2)
INSERT INTO `user_sticker` (user_id, sticker_id, estado, created_at) 
VALUES 
(2, @S1_EPICA, 'EN_COLECCION', NOW()), -- Figurita RARA obtenida
(2, @S1_EPICA, 'DUPLICADA', NOW()),    -- Duplicado para probar el reporte
(2, @S1_RARA, 'EN_TRADE', NOW()),       -- En trade para probar concurrencia
(2, @S1_COMUN, 'EN_COLECCION', NOW()),
(2, @S1_COMUN, 'DUPLICADA', NOW());

-- Simula la colección de USER B (ID 3)
INSERT INTO `user_sticker` (user_id, sticker_id, estado, created_at) 
VALUES 
(3, @S2_EPICA, 'EN_COLECCION', NOW()),
(3, @S2_RARA_ALB2, 'EN_COLECCION', NOW()),
(3, @S1_COMUN, 'EN_COLECCION', NOW()),
(3, @S1_COMUN, 'DUPLICADA', NOW()),
(3, @S1_COMUN, 'DUPLICADA', NOW()); -- 10 Registros mínimos aquí


-- =================================================================
-- FINALIZAR TRANSACCIÓN
-- =================================================================
COMMIT;
SET FOREIGN_KEY_CHECKS = 1;