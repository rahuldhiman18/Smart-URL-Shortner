const API_BASE_URL= "http://localhost:8080/api";
export const shortenUrl = async(longUrl) => {
   const response = await fetch(`${API_BASE_URL}/shorten`, {
    method: "POST",
    headers : {
        "Content-Type": "application/json",
    },
    body: JSON.stringify({
        originalUrl: longUrl,
    }),
   });
   const data = await response.json();
   return data;
};