CREATE DATABASE Weathipe
    WITH
    OWNER = "root"
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


INSERT INTO public.weather (id, cloud_base_high, cloud_base_low, cloud_cover_high, cloud_cover_low, dew_point_high, dew_point_low, humidity_high, humidity_low, rain_intensity_high, rain_intensity_low, snow_intensity_high, snow_intensity_low, temperature_high, temperature_low) VALUES (1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -4);
INSERT INTO public.weather (id, cloud_base_high, cloud_base_low, cloud_cover_high, cloud_cover_low, dew_point_high, dew_point_low, humidity_high, humidity_low, rain_intensity_high, rain_intensity_low, snow_intensity_high, snow_intensity_low, temperature_high, temperature_low) VALUES (2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0);
INSERT INTO public.weather (id, cloud_base_high, cloud_base_low, cloud_cover_high, cloud_cover_low, dew_point_high, dew_point_low, humidity_high, humidity_low, rain_intensity_high, rain_intensity_low, snow_intensity_high, snow_intensity_low, temperature_high, temperature_low) VALUES (3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 4);
INSERT INTO public.weather (id, cloud_base_high, cloud_base_low, cloud_cover_high, cloud_cover_low, dew_point_high, dew_point_low, humidity_high, humidity_low, rain_intensity_high, rain_intensity_low, snow_intensity_high, snow_intensity_low, temperature_high, temperature_low) VALUES (4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 6);
INSERT INTO public.weather (id, cloud_base_high, cloud_base_low, cloud_cover_high, cloud_cover_low, dew_point_high, dew_point_low, humidity_high, humidity_low, rain_intensity_high, rain_intensity_low, snow_intensity_high, snow_intensity_low, temperature_high, temperature_low) VALUES (5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 10);
INSERT INTO public.weather (id, cloud_base_high, cloud_base_low, cloud_cover_high, cloud_cover_low, dew_point_high, dew_point_low, humidity_high, humidity_low, rain_intensity_high, rain_intensity_low, snow_intensity_high, snow_intensity_low, temperature_high, temperature_low) VALUES (6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 16);
INSERT INTO public.weather (id, cloud_base_high, cloud_base_low, cloud_cover_high, cloud_cover_low, dew_point_high, dew_point_low, humidity_high, humidity_low, rain_intensity_high, rain_intensity_low, snow_intensity_high, snow_intensity_low, temperature_high, temperature_low) VALUES (7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 22);



INSERT INTO public.dish_type (id,dish_type_label) VALUES (1,'alcohol cocktail');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (2,'biscuits and cookies');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (3,'bread');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (4,'cereals');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (5,'condiments and sauces');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (6,'desserts');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (7,'drinks');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (8,'egg');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (9,'ice cream and custard');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (10,'main course');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (11,'pancake');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (12,'pasta');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (13,'pastry');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (14,'pies and tarts');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (15,'pizza');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (16,'preps');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (17,'preserve');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (18,'salad');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (19,'sandwiches');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (20,'seafood');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (21,'side dish');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (22,'soup');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (23,'special occasions');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (24,'starter');
INSERT INTO public.dish_type (id,dish_type_label) VALUES (25,'sweets');




INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (4,5,2);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (5,6,2);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (6,7,2);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (7,8,3);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (8,9,3);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (9,10,3);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (10,11,4);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (11,12,4);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (12,5,4);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (13,6,5);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (14,7,5);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (15,8,5);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (16,9,6);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (17,10,6);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (18,11,6);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (19,12,7);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (20,5,7);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (21,6,7);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (22,7,1);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (23,8,2);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (24,9,3);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (25,10,4);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (26,11,5);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (27,12,6);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (28,1,7);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (29,2,1);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (30,3,2);
INSERT INTO public.food_for_weather (food_weather_id, dish_type, weather_id) VALUES (31,4,3);
