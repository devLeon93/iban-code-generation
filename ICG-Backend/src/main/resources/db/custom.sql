/*Search Region*/
select cdc,name,parent_cdc from Locality  WHERE cdc = parent_cdc;

/*Search Localities */
select parent_cdc,name from locality where cdc = '0100';

/*Search iban code*/
select i.iban
from iban i
         inner join eco_code ec on i.code = ec.code
         inner join locality l on i.parent_cdc = l.parent_cdc
where ec.code = '111121' and l.cdc = '0100' and l.parent_cdc ='0110' and i.budget_year = 2017




