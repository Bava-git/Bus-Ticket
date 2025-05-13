import { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import { toast } from 'sonner';
import { format } from 'date-fns';
import { jwtDecode } from "jwt-decode";
import jsPDF from "jspdf";
import html2canvas from "html2canvas";

const History = () => {

    const [PassengerHistory, setPassengerHistory] = useState([]);
    let token = localStorage.getItem("token") || "";
    const decoded = jwtDecode(token);
    let passenger_id = decoded.userId;


    useEffect(() => {
        fetchData();
    }, [])

    const fetchData = async () => {
        try {
            let response = await axios.get(`http://localhost:3000/passengerbookingInfo`, {
                headers: {
                    "Content-type": "Application/json",
                    Authorization: `Bearer ${localStorage.getItem("token")}`
                }
            })
            let getdata = response.data;
            setPassengerHistory(getdata.filter((element) => {
                return element.passenger_id === passenger_id;
            }))
        } catch (error) {
            console.log("Profile " + error);
        }
    }

    // const downloadPDF = () => {
    //     const element = document.getElementById("pdf-content"); // Capture this div
    //     html2canvas(element).then((canvas) => {
    //         const imgData = canvas.toDataURL("image/png");
    //         const pdf = new jsPDF("p", "mm", "a4");
    //         pdf.addImage(imgData, "PNG", 10, 10, 190, 0);
    //         pdf.save("busticket.pdf");
    //     });
    // };

    return (
        <div className="profilecontainer" >
            <h3 className='profilecontainer-title'>Booking History</h3>
            <div className="profilepage" >
                {PassengerHistory.map((element, index) => (
                    <div className="profilepage-seperator" key={index} >
                        <div className='profilepage-data' id="pdf-content">
                            <h3>PNR number: {element.pnr_Number}</h3>
                            <div>
                                <div className="profilepage-field">
                                    <p>Passenger BookingInfo id: </p>
                                    <p>{element.passengerBookingInfo_id}</p>
                                </div>
                                <div className="profilepage-field">
                                    <p>Booked Date/Time: </p>
                                    <p>{element.booking_datetime}</p>
                                </div>
                            </div>
                            <div>
                                <div className="profilepage-field">
                                    <p>Passenger id: </p>
                                    <p>{element.passenger_id}</p>
                                </div>
                                <div className="profilepage-field">
                                    <p>Passenger Name: </p>
                                    <p>{element.passenger_name}</p>
                                </div>
                                <div className="profilepage-field">
                                    <p>Passenger Gender: </p>
                                    <p>{element.passenger_gender}</p>
                                </div>
                                <div className="profilepage-field">
                                    <p>Passenger Mobile: </p>
                                    <p>{element.passenger_mobile}</p>
                                </div>
                                <div className="profilepage-field">
                                    <p>Passenger Email: </p>
                                    <p>{element.passenger_email}</p>
                                </div>
                            </div>
                            <div>
                                <div className="profilepage-field">
                                    <p>Booking Info id: </p>
                                    <p>{element.bookingInfo_id}</p>
                                </div>
                                <div className="profilepage-field">
                                    <p>Seat Number: </p>
                                    <p>{element.seat_num}</p>
                                </div>
                                <div className="profilepage-field">
                                    <p>Payment Type: </p>
                                    <p>{element.paymentType}</p>
                                </div>
                            </div>
                        </div>
                        <div className='downloadBNDiv'>
                            {/* <button className='downloadBN' onClick={() => { downloadPDF() }}>Download</button> */}
                        </div>
                    </div>))}
            </div>
        </div >
    )
}

export { History };