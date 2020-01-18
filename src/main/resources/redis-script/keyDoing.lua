
--[[
并发控制 
KEYS[1]:key
ARGV[1]:request numbers
ARGV[2]:expires times seconds
--]]

local result = redis.call('SETNX',KEYS[1],ARGV[1]); 
if (tonumber(result) == 1) then 
	redis.call('expire', KEYS[1], ARGV[2]); 
end

return result;