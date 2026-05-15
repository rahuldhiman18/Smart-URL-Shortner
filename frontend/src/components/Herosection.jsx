import { shortenUrl } from "../services/urlService";
import { useState} from "react";
import "./HeroSection.css";
function HeroSection(){
    const[longUrl,setLongUrl] = useState("")

    const handleShortenUrl = async()=> {
        const data = await shortenUrl(longUrl);
        console.log(data);
    };
    return (
        <section className="hero-section" id = "shorten">
            <div className="hero-content">

            <p className="hero-badge">Smart Url Shortner</p>
             <h1> Shorten links. Track clicks. Grow smarter</h1>

             <p className="hero-descripition">
                Create clean and trackable short URLs with analytics and QRcode support
             </p>

             <div className="url-input-box">
                <input type ="text" placeholder="Paste your long URLs ..."
                value ={longUrl} 
                onChange ={(event) => setLongUrl(event.target.value)}
                />
                <button onClick={handleShortenUrl}> Short Url </button>
             </div>
             {longUrl && (
             <p className="typed-url-preview">
                You typed: {longUrl}
             </p>
             )}
             </div>
        </section>
    );
}

export default HeroSection;