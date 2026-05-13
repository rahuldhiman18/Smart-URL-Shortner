 import "./Navbar.css";
 function Navbar(){
    return (
        <nav className="navbar">
            <h2 className="navbar-logo">SmartShort</h2>

            <div className=" navbar-links">
                <a href="#shorten">Shorten</a>
                <a href="#analytics">Analytics</a>
                <a href="#QRCode">QRCode</a>
            </div>

            <button className="navabr-button">Get Started</button>
        </nav>
    );
}

export default Navbar;