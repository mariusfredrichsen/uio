import os
import pandas as pd
from sklearn.datasets import fetch_california_housing

def download_housing():
    """
    Downloads the California Housing dataset and saves it as a CSV
    in 'Datasets/Housing'.
    """
    base_dir = os.path.dirname(os.path.abspath(__file__))
    save_dir = os.path.join(base_dir, 'Housing')
    
    print(f"🏠 Preparing Housing Dataset in: {save_dir}")
    os.makedirs(save_dir, exist_ok=True)

    print("Fetching data from Scikit-Learn...")
    housing = fetch_california_housing(as_frame=True)
    df = housing.frame
    
    # Save to CSV so students can load it with pandas later
    csv_path = os.path.join(save_dir, "california_housing.csv")
    df.to_csv(csv_path, index=False)
    
    print(f"✅ Done! Saved CSV to: {csv_path}")
    print(f"   - Rows: {len(df)}")
    print(f"   - Columns: {list(df.columns)}")

if __name__ == "__main__":
    download_housing()