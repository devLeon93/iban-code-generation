/*Search Region*/
select cdc,name from Locality  WHERE cdc = parent_cdc;

/*Search Localities */
select parent_cdc,name from locality where cdc = '0100';
