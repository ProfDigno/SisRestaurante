ALTER TABLE venta ADD COLUMN monto_venta_total numeric(14,0) DEFAULT 0;
ALTER TABLE caja_detalle ADD COLUMN monto_venta_gtigo numeric(14,0) DEFAULT 0;
ALTER TABLE caja_detalle ADD COLUMN monto_venta_gpersonal numeric(14,0) DEFAULT 0;
ALTER TABLE caja_detalle ADD COLUMN monto_venta_transferencia numeric(14,0) DEFAULT 0;
ALTER TABLE caja_detalle ADD COLUMN monto_venta_pix numeric(14,0) DEFAULT 0;