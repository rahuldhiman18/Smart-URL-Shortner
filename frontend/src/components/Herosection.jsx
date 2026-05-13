import "./Herosection.css";
function Herosection(){
    return (
        <section className="hero-section" id = "shorten">
            <div className="hero-content">

            <p className="hero-badge">Smart Url Shortner</p>
             <h1> Shorten links. Track clicks. Grow smarter</h1>

             <p className="hero-descripition">
                Create clean and trackable short URLs with analytics and QRcode support
             </p>

             <div className="url-input-box">
                <input type ="text" placeholder="Paste your long URLs ..." />
                <button> Short Url </button>
             </div>
             </div>
        </section>
    );
}

export default Herosection;