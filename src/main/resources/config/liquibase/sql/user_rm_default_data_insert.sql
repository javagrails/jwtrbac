INSERT INTO public.user_rm (user_id, service_id, url, http_method, combine, combine_hash, active) VALUES
(5, 'app1', 'api/countries/**', 'GET', '5_app1_api/countries/**', 'combine_hash_1', TRUE),
(5, 'app1', 'api/countries/**', 'POST', '5_app1_api/countries/**', 'combine_hash_2', TRUE),
(5, 'app1', 'api/countries/**', 'PUT', '5_app1_api/countries/**', 'combine_hash_3', TRUE),
(5, 'app1', 'api/countries/**', 'DELETE', '5_app1_api/countries/**', 'combine_hash_4', TRUE),
(6, 'app1', 'api/countries/**', 'GET', '5_app1_api/countries/**', 'combine_hash_5', TRUE);
