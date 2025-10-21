-- =================================================================
-- CONFIGURACIÓN INICIAL Y PREVENCIÓN DE ERRORES DE FK
-- =================================================================
USE album;
SET FOREIGN_KEY_CHECKS = 0; -- Deshabilita FK checks temporalmente para evitar errores de orden
START TRANSACTION;

-- =================================================================
-- 1. INSERTAR USUARIOS (TABLA PADRE)
-- =================================================================
-- 1 Admin y 4 Users para tener al menos 5.
INSERT INTO `user` (username, email, password, nombre, apellido, `role`, created_at) VALUES 
('admin_creator2', 'admin2@album.com', 'hash1', 'Victor', 'Admin', 'ADMIN', NOW()),
('user1', 'user1@test.com', 'hash2', 'Alice', 'Smith', 'USER', NOW()), -- ID de referencia: @USER1_ID
('user2', 'user2@test.com', 'hash3', 'Bob', 'Jones', 'USER', NOW()),   -- ID de referencia: @USER2_ID
('user3', 'user3@test.com', 'hash4', 'Charlie', 'Brown', 'USER', NOW()),
('user4', 'user4@test.com', 'hash5', 'Dana', 'White', 'USER', NOW()),
('user5', 'user5@test.com', 'hash6', 'Eve', 'Black', 'USER', NOW());

SET @ADMIN_ID = 1;
SET @USER1_ID = 2; 
SET @USER2_ID = 3; 


-- =================================================================
-- 2. INSERTAR ÁLBUMES (Depende de USER.creador_admin_id)
-- =================================================================
-- Insertamos 3 Álbumes (FÁCIL, MEDIO, DIFÍCIL)
INSERT INTO `album` (titulo, descripcion, categoria, dificultad, creador_admin_id, total_figuritas, publicado, created_at)
VALUES 
('Flora y Fauna', 'Especies autóctonas.', 'Naturaleza', 'FACIL', @ADMIN_ID, 30, TRUE, NOW()), -- ID 1: @ALBUM1_ID
('Exploración Galáctica', 'Naves y planetas.', 'Ciencia Ficción', 'DIFICIL', @ADMIN_ID, 50, TRUE, NOW()), -- ID 2: @ALBUM2_ID
('Motos Clásicas', 'Modelos icónicos.', 'Vehículos', 'MEDIO', @ADMIN_ID, 40, TRUE, NOW()); -- ID 3

SET @ALBUM1_ID = 1;
SET @ALBUM2_ID = 2;


-- =================================================================
-- 3. INSERTAR STICKERS (Depende de ALBUM.id)
-- Objetivo: Al menos 10 stickers totales.
-- =================================================================
-- Álbum 1 (Flora y Fauna): 5 Stickers
INSERT INTO `sticker` (album_id, nombre, numero, rareza, stock_total, stock_disponible, imagen_url, created_at)
VALUES 
(@ALBUM1_ID, 'Sapo Común', 1, 'COMUN', 2000, 1900, '/img/s1_1.png', NOW()), -- ID 1
(@ALBUM1_ID, 'Orquídea Rara', 2, 'RARA', 300, 280, '/img/s1_2.png', NOW()), -- ID 2
(@ALBUM1_ID, 'Cóndor Andino', 3, 'EPICA', 50, 5, '/img/s1_3.png', NOW()),   -- ID 3 (Más Rara)
(@ALBUM1_ID, 'Árbol Milenario', 4, 'COMUN', 1500, 1500, '/img/s1_4.png', NOW()),
(@ALBUM1_ID, 'Flor Silvestre', 5, 'COMUN', 1500, 1500, '/img/s1_5.png', NOW()); 

SET @S1_COMUN = 1;
SET @S1_RARA = 2;
SET @S1_EPICA = 3;

-- Álbum 2 (Exploración Galáctica): 5 Stickers
INSERT INTO `sticker` (album_id, nombre, numero, rareza, stock_total, stock_disponible, imagen_url, created_at)
VALUES 
(@ALBUM2_ID, 'Droide de Servicio', 1, 'COMUN', 1000, 800, '/img/s2_1.png', NOW()), -- ID 6
(@ALBUM2_ID, 'Nave X-Wing', 2, 'RARA', 200, 150, '/img/s2_2.png', NOW()), -- ID 7
(@ALBUM2_ID, 'Emperador Zylos', 3, 'EPICA', 70, 10, '/img/s2_3.png', NOW()), -- ID 8 (Rara)
(@ALBUM2_ID, 'Planeta Rojo', 4, 'COMUN', 1200, 1200, '/img/s2_4.png', NOW()),
(@ALBUM2_ID, 'Comandante Eva', 5, 'RARA', 200, 190, '/img/s2_5.png', NOW()); 

SET @S2_COMUN = 6;
SET @S2_RARA = 7;
SET @S2_EPICA = 8;


-- =================================================================
-- 4. INSERTAR REWARD (Depende de ALBUM.id)
-- =================================================================
INSERT INTO `reward` (album_id, tipo, payload_json, created_at) 
VALUES (@ALBUM1_ID, 'BADGE_FLORA', '{"nombre":"Maestro Flora"}', NOW());
SET @REWARD1_ID = LAST_INSERT_ID(); 
INSERT INTO `reward` (album_id, tipo, payload_json, created_at) 
VALUES (@ALBUM2_ID, 'AVATAR_NAVE', '{"url":"/assets/nave_epic.png"}', NOW());
SET @REWARD2_ID = LAST_INSERT_ID();


-- =================================================================
-- 5. INSERTAR PACKS Y PACK_STICKER (10+ Registros)
-- =================================================================
-- Insertamos 10 Packs totales para el reporte de Tasa de Apertura (10 registros)

-- USER1 (ID 2) compra 6 packs (3 en Album 1, 3 en Album 2)
INSERT INTO `pack` (user_id, album_id, created_at) VALUES (@USER1_ID, @ALBUM1_ID, NOW()), (@USER1_ID, @ALBUM1_ID, NOW()), (@USER1_ID, @ALBUM1_ID, NOW());
INSERT INTO `pack` (user_id, album_id, created_at) VALUES (@USER1_ID, @ALBUM2_ID, NOW()), (@USER1_ID, @ALBUM2_ID, NOW()), (@USER1_ID, @ALBUM2_ID, NOW());

-- USER2 (ID 3) compra 4 packs (todos en Album 1)
INSERT INTO `pack` (user_id, album_id, created_at) VALUES (@USER2_ID, @ALBUM1_ID, NOW()), (@USER2_ID, @ALBUM1_ID, NOW()), (@USER2_ID, @ALBUM1_ID, NOW());
INSERT INTO `pack` (user_id, album_id, created_at) VALUES (@USER2_ID, @ALBUM1_ID, NOW());

-- Asumimos que los IDs de los packs son del 1 al 10.
SET @PACK1_ID = 1;
SET @PACK10_ID = 10;

-- Insertar contenido en Pack_Sticker (Mínimo 10 registros, pondremos 15 para 3 packs)
-- Pack 1: Contiene la figura épica (S1_EPICA)
INSERT INTO `pack_sticker` (pack_id, sticker_id) VALUES 
(1, @S1_COMUN), (1, @S1_COMUN), (1, @S1_RARA), (1, @S1_EPICA), (1, @S1_COMUN), -- Pack 1 (5 items)
(3, @S1_COMUN), (3, @S1_RARA), (3, @S1_COMUN), (3, @S1_COMUN), (3, @S1_COMUN), -- Pack 3 (5 items)
(5, @S2_COMUN), (5, @S2_EPICA), (5, @S2_COMUN), (5, @S2_COMUN), (5, @S2_RARA); -- Pack 5 (5 items)


-- =================================================================
-- 6. INSERTAR USER_STICKER (10+ Registros en la colección)
-- =================================================================
-- Colección de USER1 (ID 2): tiene duplicados
INSERT INTO `user_sticker` (user_id, sticker_id, estado, created_at) VALUES 
(2, @S1_COMUN, 'EN_COLECCION', NOW()), 
(2, @S1_RARA, 'EN_COLECCION', NOW()),
(2, @S1_RARA, 'DUPLICADA', NOW()), -- Duplicada
(2, @S1_EPICA, 'EN_TRADE', NOW()),  -- En trade
(2, @S2_COMUN, 'EN_COLECCION', NOW()),
(2, @S2_EPICA, 'EN_COLECCION', NOW());

-- Colección de USER2 (ID 3): tiene duplicados para testing
INSERT INTO `user_sticker` (user_id, sticker_id, estado, created_at) VALUES 
(3, @S1_COMUN, 'EN_COLECCION', NOW()),
(3, @S1_COMUN, 'DUPLICADA', NOW()),
(3, @S1_COMUN, 'DUPLICADA', NOW()),
(3, @S2_RARA, 'EN_COLECCION', NOW());


-- =================================================================
-- 7. INSERTAR USER_REWARD (Completitud) (3 registros reclamados)
-- =================================================================
-- USER1 completa el Album 1
INSERT INTO `user_reward` (user_id, album_id, reward_id, estado, claimed_at)
VALUES (@USER1_ID, @ALBUM1_ID, @REWARD1_ID, 'RECLAMADO', NOW());

-- USER2 completa el Album 1
INSERT INTO `user_reward` (user_id, album_id, reward_id, estado, claimed_at)
VALUES (@USER2_ID, @ALBUM1_ID, @REWARD1_ID, 'RECLAMADO', NOW());

-- USER3 completa el Album 2
INSERT INTO `user_reward` (user_id, album_id, reward_id, estado, claimed_at)
VALUES (4, @ALBUM2_ID, @REWARD2_ID, 'RECLAMADO', NOW());


-- =================================================================
-- FINALIZAR TRANSACCIÓN
-- =================================================================
COMMIT;
SET FOREIGN_KEY_CHECKS = 1; -- Restablece la verificación de FK