INSERT INTO public.user_rm (user_id, service_id, url, http_method, combine, combine_hash, active) VALUES
(5, 'app1', 'api/countries/**', 'GET', '5_app1_get_api/countries/**', 'combine_hash_1_5', TRUE),
(5, 'app1', 'api/countries/**', 'POST', '5_app1_post_api/countries/**', 'combine_hash_2_5', TRUE),
(5, 'app1', 'api/countries/**', 'PUT', '5_app1_put_api/countries/**', 'combine_hash_3_5', TRUE),
(5, 'app1', 'api/countries/**', 'DELETE', '5_app1_delete_api/countries/**', 'combine_hash_4_5', TRUE),
(6, 'app1', 'api/countries/**', 'GET', '6_app1_get_api/countries/**', 'combine_hash_5_6', TRUE),
(6, 'app1', 'api/countries/**', 'POST', '6_app1_post_api/countries/**', 'combine_hash_6_6', TRUE),
(7, 'app1', 'api/countries/**', 'POST', '7_app1_post_api/countries/**', 'combine_hash_7_7', TRUE),
(7, 'app1', 'api/countries/**', 'DELETE', '7_app1_delete_api/countries/**', 'combine_hash_8_7', TRUE);
