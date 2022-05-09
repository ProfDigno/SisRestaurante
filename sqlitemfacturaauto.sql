INSERT INTO item_factura_auto( fecha_creado, creado_por, descripcion, cantidad, 
            precio_venta, precio_compra, iva, total_exenta, total_gravada_5, 
            total_gravada_10, cuenta_exenta, cuenta_5, cuenta_10, fk_idfactura_autoimpresor, 
            fk_idproducto)
SELECT CURRENT_TIMESTAMP as fecha_creado,('digno alfredo') as creado_por,
iv.descripcion, iv.cantidad,iv.precio_venta, iv.precio_compra,         
       case when iv.tipo='P' then p.iva
            when iv.tipo='N' then p.iva
            when iv.tipo='I' then 10
            when iv.tipo='D' then 10
            else 0 end as iva,
 case when iv.tipo='P' and p.iva=0 then (iv.precio_venta*iv.cantidad) 
      when iv.tipo='N' and p.iva=0 then (iv.precio_venta*iv.cantidad) 
      else 0 end as total_exenta,
 case when iv.tipo='P' and p.iva=5 then (iv.precio_venta*iv.cantidad) 
      when iv.tipo='N' and p.iva=5 then (iv.precio_venta*iv.cantidad) 
      else 0 end as total_gravada_5,
 case when iv.tipo='P' and p.iva=10 then (iv.precio_venta*iv.cantidad) 
      when iv.tipo='N' and p.iva=10 then (iv.precio_venta*iv.cantidad) 
      when iv.tipo='I' then (iv.precio_venta*iv.cantidad) 
      when iv.tipo='D' then (iv.precio_venta*iv.cantidad) 
      else 0 end as total_gravada_10,
case when iv.tipo='P' and p.iva=0 then (select pc.descripcion from producto_cuenta pc where pc.idproducto_cuenta=p.fk_idproducto_cuenta) 
      when iv.tipo='N' and p.iva=0 then (select pc.descripcion from producto_cuenta pc where pc.idproducto_cuenta=p.fk_idproducto_cuenta)
      else '---' end as cuenta_exenta,
 case when iv.tipo='P' and p.iva=5 then (select pc.descripcion from producto_cuenta pc where pc.idproducto_cuenta=p.fk_idproducto_cuenta)
      when iv.tipo='N' and p.iva=5 then (select pc.descripcion from producto_cuenta pc where pc.idproducto_cuenta=p.fk_idproducto_cuenta)
      else '---' end as cuenta_5,
 case when iv.tipo='P' and p.iva=10 then (select pc.descripcion from producto_cuenta pc where pc.idproducto_cuenta=p.fk_idproducto_cuenta)
      when iv.tipo='N' and p.iva=10 then (select pc.descripcion from producto_cuenta pc where pc.idproducto_cuenta=p.fk_idproducto_cuenta)
      when iv.tipo='I' then 'CUNETA INGREDIENTE' 
      when iv.tipo='D' then 'CUENTA DELIVERY'
      else '---' end as cuenta_10,
(select COALESCE(max(idfactura_autoimpresor + 1),1) from factura_autoimpresor) as idfactura_autoimpresor,
case when iv.tipo='P' then p.idproducto
     when iv.tipo='N' then p.idproducto
     when iv.tipo='I' then 0
     when iv.tipo='D' then 0
     else 0 end as fk_idproducto
  FROM item_venta iv,producto p
where iv.fk_idproducto=p.idproducto
and iv.fk_idventa=11585;
